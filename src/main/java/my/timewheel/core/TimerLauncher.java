package my.timewheel.core;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import my.timewheel.task.Task;
import my.timewheel.task.TaskEntry;
import my.timewheel.task.TaskList;
import my.timewheel.wheel.TimeWheel;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Data
@Slf4j
public class TimerLauncher implements Timer
{

    /**
     * 底层时间轮
     */
    private TimeWheel            timeWheel;
    /**
     * 一个 Timer 只有一个延时队列
     */
    private DelayQueue<TaskList> delayQueue = new DelayQueue<>();

    /**
     * 轮询 delayQueue 获取过期任务线程
     */
    private ExecutorService bossThreadPool;
    /**
     * 过期任务执行线程
     */
    private ExecutorService workerThreadPool;

    // =============================================================================

    @SuppressWarnings("all")
    public TimerLauncher()
    {
        timeWheel        = new TimeWheel(20, 20, System.currentTimeMillis(), delayQueue);
        bossThreadPool   = Executors.newFixedThreadPool(1);
        workerThreadPool = Executors.newFixedThreadPool(10);

        bossThreadPool.execute(new Runnable()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    // 推进时间轮
                    advanceClock(5000);
                }
            }
        });
    }

    /**
     * 推进指针, 获取过期任务
     */
    @Override
    public synchronized void advanceClock(long timeoutMs)
    {
        try
        {
            TaskList bucket = delayQueue.poll(timeoutMs, TimeUnit.MILLISECONDS);
            if (bucket != null)
            {
                // 推进时间
                timeWheel.advanceClock(bucket.getExpiration());
                // 执行过期任务(包含降级)
                bucket.clear(this::addTaskEntry);
            }
        }
        catch (InterruptedException e)
        {
            log.info("advanceClock error");
        }
    }

    // =============================================================================

    @Override
    public void addTask(Task task)
    {
        TaskEntry entry = new TaskEntry(task, task.getDelayMs() + System.currentTimeMillis());
        addTaskEntry(entry);
    }

    private void addTaskEntry(TaskEntry entry)
    {
        if (!timeWheel.addTaskEntry(entry))
        {
            // 任务已到期
            Task task = entry.getTask();
            workerThreadPool.execute(task);
        }
    }

    @Override
    public void shutdown()
    {
        bossThreadPool.shutdown();
        workerThreadPool.shutdown();
        timeWheel = null;
    }
}
