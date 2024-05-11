package more.timewheel.core;

import lombok.extern.slf4j.Slf4j;
import more.timewheel.task.Task;
import more.timewheel.task.TaskEntry;
import more.timewheel.task.TaskList;
import more.timewheel.wheel.TimeWheel;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

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
        this.taskExecutor = Executors.newFixedThreadPool(1, new ThreadFactory()
        {
            @Override
            public Thread newThread(Runnable r)
            {
                return KafkaThread.nonDaemon("executor-" + executorName, r);
            }
        });

        reaper.start();
    }

    /**
     * 推进时间轮的 currentTimeMs, 定时任务到期则进行处理
     */
    @Override
    public synchronized void advanceClock(long timeoutMs) throws InterruptedException
    {
        // 获取到期的 bucket
        TaskList bucket = delayQueue.poll(timeoutMs, TimeUnit.MILLISECONDS);
        if (bucket != null)
        {
            // 加写锁的原因: 避免多线程同时推进 TimeWheel.currentTimeMs, 这是一个写操作
            writeLock.lock();
            try
            {
                while (bucket != null)
                {
                    // 推进时间轮的 currentTimeMs
                    timeWheel.advanceClock(bucket.getExpiration());
                    // 任务的降级: 清除任务, 重新添加到时间轮, 如果添加失败就代表定时任务到期
                    bucket.flush(new Consumer<TaskEntry>()
                    {
                        @Override
                        public void accept(TaskEntry taskEntry)
                        {
                            addTaskEntry(taskEntry);
                        }
                    });
                    // 重新获取到期的 bucket
                    bucket = delayQueue.poll();
                }
            }
            finally
            {
                writeLock.unlock();
            }
        }
    }

    // =============================================================================

    @Override
    public void addTask(Task task)
    {
        // 在没有线程持有写锁的前提下
        // 多个线程能够 "同时" 向时间轮 "添加" 定时任务
        // 加读锁的原因: 添加定时任务涉及到对 TimeWheel.currentTimeMs 的读
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
        if (!timeWheel.addTaskEntry(entry))
        {
            // 定时任务到期
            Task task = entry.task;
            taskExecutor.submit(task);
        }
    }

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
