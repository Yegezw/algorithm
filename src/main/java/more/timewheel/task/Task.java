package more.timewheel.task;

import more.timewheel.core.SystemTimer;

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

    // =============================================================================

    /**
     * <p>用于实现任务的取消功能
     * <p>该属性的写操作需要保证线程安全, 创建 TaskEntry 期间进行取消需要等待
     */
    private volatile TaskEntry taskEntry;

    /**
     * 取消该定时任务
     */
    public void cancel()
    {
        synchronized (this)
        {
            if (taskEntry != null)
            {
                taskEntry.remove(); // 它是线程安全函数
            }
            taskEntry = null;
        }
    }

    /**
     * 创建 TaskEntry 时调用
     *
     * @see SystemTimer#add(Task)
     */
    final void setTaskEntry(TaskEntry entry)
    {
        synchronized (this)
        {
            // 如果该 Task 已被现有的 TaskEntry 持有, 我们将首先删除该 TaskEntry
            if (taskEntry != null && taskEntry != entry)
            {
                taskEntry.remove(); // 它是线程安全函数
            }

            taskEntry = entry;
        }
    }

    /**
     * 该方法会对 taskEntry 进行读操作<br>
     * 该方法并没有加 synchronized, 因此 taskEntry 需要被 volatile 修饰, 用于保证读操作的可见性
     */
    TaskEntry getTaskEntry()
    {
        return taskEntry;
    }
}
