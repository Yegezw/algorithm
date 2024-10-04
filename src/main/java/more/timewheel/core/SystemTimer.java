package more.timewheel.core;

import lombok.extern.slf4j.Slf4j;
import more.timewheel.task.Task;
import more.timewheel.task.TaskEntry;
import more.timewheel.task.TaskList;
import more.timewheel.task.TimeWheel;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 系统定时器
 */
@Slf4j
public class SystemTimer implements Timer
{

    /**
     * 收割者
     */
    class Reaper extends Thread
    {
        private static final long WORK_TIMEOUT_MS = 200L;

        public Reaper()
        {
            super("SystemTimer-Reaper");
        }

        @Override
        @SuppressWarnings("all")
        public void run()
        {
            while (true)
            {
                try
                {
                    advanceClock(WORK_TIMEOUT_MS);
                    if (closing.get() && taskCounter.get() == 0)
                    {
                        // 转移完成
                        transferComplete.countDown();
                        log.info("Reaper is closed");
                        break;
                    }
                }
                catch (InterruptedException ignore)
                {
                }
            }
        }
    }

    // =============================================================================

    /**
     * 底层时间轮
     */
    private final TimeWheel            timeWheel;
    /**
     * 一个 Timer 只有一个延时队列
     */
    private final DelayQueue<TaskList> delayQueue;
    /**
     * TimeWheel 中的任务数量
     */
    private final AtomicInteger        taskCounter;

    // ----------------------------------------

    /**
     * 收割线程, 轮询 delayQueue 获取到期的定时任务
     */
    @SuppressWarnings("all")
    private final Reaper          reaper;
    /**
     * 任务线程, 处理到期的定时任务
     */
    private final ExecutorService taskExecutor;

    // ----------------------------------------

    private final ReentrantReadWriteLock           readWriteLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock  readLock      = readWriteLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock     = readWriteLock.writeLock();

    // ----------------------------------------

    /**
     * SystemTimer 是否在关闭中
     */
    private final AtomicBoolean  closing          = new AtomicBoolean(false);
    /**
     * 等待 reaper 将 timeWheel 中的任务都转移到 taskExecutor 中
     */
    private final CountDownLatch transferComplete = new CountDownLatch(1);

    // =============================================================================

    public SystemTimer(String executorName)
    {
        this(executorName, 1, 20, System.currentTimeMillis());
    }

    public SystemTimer(String executorName, long tickMs, int wheelSize, long startMs)
    {
        this.delayQueue   = new DelayQueue<>();
        this.taskCounter  = new AtomicInteger(0);
        this.timeWheel    = new TimeWheel(
                tickMs,
                wheelSize,
                startMs,
                taskCounter,
                delayQueue
        );
        this.reaper       = new Reaper();
        this.taskExecutor = Executors.newFixedThreadPool(
                1,
                runnable -> KafkaThread.nonDaemon("SystemTimer-executor-" + executorName, runnable)
        );

        reaper.start();
    }

    /**
     * 推进时间轮的 currentTimeMs, 定时任务到期则进行处理
     *
     * @return 是否执行了任何任务
     */
    @Override
    public synchronized boolean advanceClock(long timeoutMs) throws InterruptedException
    {
        // 获取到期的 bucket
        TaskList bucket = delayQueue.poll(timeoutMs, TimeUnit.MILLISECONDS);
        if (bucket != null)
        {
            // 加写锁的原因: 避免多线程同时推进 TimeWheel.currentTimeMs
            writeLock.lock();
            try
            {
                while (bucket != null)
                {
                    // 推进 TimeWheel.currentTimeMs
                    timeWheel.advanceClock(bucket.getExpiration());

                    // TaskEntry 的降级: 如果 entry 未到期, 就会从 bucket1 移动到 bucket2
                    // 清除 bucket.TaskEntry 并重新添加到时间轮, 如果添加失败就代表定时任务 - 已取消 OR 已到期
                    bucket.flush(this::addTaskEntry);

                    // 重新获取到期的 bucket
                    bucket = delayQueue.poll(); // 非阻塞函数
                }
            }
            finally
            {
                writeLock.unlock();
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    // =============================================================================

    @Override
    public void add(Task task)
    {
        if (closing.get())
        {
            throw new RuntimeException("SystemTimer is closing");
        }

        // 在没有线程持有写锁的前提下
        // 多线程能 "同时向时间轮添加" 定时任务
        readLock.lock();
        try
        {
            TaskEntry entry = new TaskEntry(task, task.delayMs + System.currentTimeMillis());
            addTaskEntry(entry);
        }
        finally
        {
            readLock.unlock();
        }
    }

    private void addTaskEntry(TaskEntry entry)
    {
        // 定时任务 - 已取消 OR 已到期
        if (!timeWheel.add(entry))
        {
            // 定时任务 - 未取消
            if (!entry.cancelled())
            {
                Task task = entry.task;
                taskExecutor.submit(task);
            }
        }
    }

    // =============================================================================

    @Override
    public int size()
    {
        return taskCounter.get();
    }

    /**
     * 阻塞函数: 已有任务执行完后关闭 SystemTimer, 在此期间拒绝接收新任务
     */
    @Override
    public void close()
    {
        log.info("SystemTimer is closing");

        try
        {
            closing.set(true);        // 标记 closing 为 true
            transferComplete.await(); // 等待 reaper 将 timeWheel 中的任务都转移到 taskExecutor 中
            taskExecutor.shutdown();  // 此时 taskExecutor.workQueue 可能会存在未执行完成的任务, 需要等待
            boolean terminated;
            do
            {
                terminated = taskExecutor.awaitTermination(5, TimeUnit.SECONDS);
            } while (!terminated);
        }
        catch (InterruptedException ignore)
        {
        }

        log.info("SystemTimer is closed");
    }

    public boolean isTerminated()
    {
        return taskExecutor.isTerminated();
    }
}
