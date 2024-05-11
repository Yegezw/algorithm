package my.timewheel.task;

/**
 * 定时任务
 */
public abstract class Task implements Runnable
{

    /**
     * 延迟时间 - 相对
     */
    public final long delayMs;

    public Task(long delayMs)
    {
        this.delayMs = delayMs;
    }
}
