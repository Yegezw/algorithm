package my.timewheel.task;

/**
 * 定时任务项
 */
public class TaskEntry
{

    /**
     * 定时任务
     */
    public final Task task;
    /**
     * 过期时间 - 绝对
     */
    public final long expirationMs;

    // ----------------------------------------

    TaskEntry prev;
    TaskEntry next;

    // =============================================================================

    public TaskEntry(Task task, long expirationMs)
    {
        this.task         = task;
        this.expirationMs = expirationMs;
        this.prev         = null;
        this.next         = null;
    }
}
