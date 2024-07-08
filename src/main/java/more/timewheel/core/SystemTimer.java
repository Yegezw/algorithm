package more.timewheel.core;

import lombok.extern.slf4j.Slf4j;
import more.timewheel.task.Task;
import more.timewheel.task.TaskEntry;
import more.timewheel.task.TaskList;
import more.timewheel.task.TimeWheel;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
            try
            {
                while (true)
                {
                    advanceClock(WORK_TIMEOUT_MS);
                }
            }
            catch (InterruptedException ignore)
            {
                log.info("Thread {} is closed", Thread.currentThread().getName());
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
    private final Reaper          reaper;
    /**
     * 任务线程, 处理到期的定时任务
     */
    private final ExecutorService taskExecutor;

    // ----------------------------------------

    private final ReentrantReadWriteLock           readWriteLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock  readLock      = readWriteLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock     = readWriteLock.writeLock();

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
                r -> KafkaThread.nonDaemon("SystemTimer-executor-" + executorName, r)
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

    @Override
    public void close()
    {
        reaper.interrupt();
        taskExecutor.shutdown();
    }
}
