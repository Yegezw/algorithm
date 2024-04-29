package my.timewheel.task;

import lombok.Getter;

/**
 * 定时任务项
 */
@Getter
public class TaskEntry
{

    /**
     * 定时任务
     */
    private final Task task;
    /**
     * 过期时间 - 绝对
     */
    private final long expireMs;

    // ----------------------------------------

    TaskEntry prev;
    TaskEntry next;

    // =============================================================================

    public TaskEntry(Task task, long expireMs)
    {
        this.task     = task;
        this.expireMs = expireMs;
        this.prev     = null;
        this.next     = null;
    }
}
