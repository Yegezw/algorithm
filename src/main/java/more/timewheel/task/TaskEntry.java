package more.timewheel.task;

import more.timewheel.core.SystemTimer;

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

    /**
     * @see SystemTimer#add(Task)
     */
    public TaskEntry(Task task, long expirationMs)
    {
        this.task         = task;
        this.expirationMs = expirationMs;

        // 如果该 Task 已被现有的 TaskEntry 持有, setTaskEntry 将删除该 TaskEntry
        if (task != null)
        {
            task.setTaskEntry(this);
        }
    }

    public boolean cancelled()
    {
        return task.getTaskEntry() != this;
    }

    // =============================================================================

    /**
     * 该属性的读写操作需要保证线程安全
     *
     * @see TaskList#add(TaskEntry)
     * @see TaskList#remove(TaskEntry)
     */
    TaskEntry prev;
    /**
     * 该属性的读写操作需要保证线程安全
     *
     * @see TaskList#add(TaskEntry)
     * @see TaskList#remove(TaskEntry)
     */
    TaskEntry next;
    /**
     * <p>用于实现任务的取消功能
     * <p>该属性的写操作需要保证线程安全
     *
     * @see TaskList#add(TaskEntry)
     * @see TaskList#remove(TaskEntry)
     */
    volatile TaskList list;

    // ----------------------------------------

    /**
     * <p>将该 TaskEntry 从所属的 TaskList 中移除, 在极少数情况下会删除失败
     * <p>
     * 该方法会对 list 进行读操作<br>
     * 该方法并没有加 synchronized, 因此 list 需要被 volatile 修饰, 用于保证读操作的可见性
     * <p/>
     *
     * @see TaskList#add(TaskEntry)
     * @see Task#cancel()
     */
    public void remove()
    {
        // bucket
        TaskList currentList = list;

        // 当前方法执行时, list 可能会改变
        // 因为其它线程可能把当前 entry 移动到另一个 list 中, 这里需要自旋, 直到 list = null
        // 在极少数情况下, 当前线程会看到 list = null 并退出循环, 但其它线程稍后会将该 entry 插入到另一个 list 中, 即 entry 删除失败
        while (currentList != null)
        {
            // 代码执行到这里时, 可能会出现的几种情况
            // (currentList == list) OR (list == null) OR (currentList != list)
            currentList.remove(this);
            currentList = list; // list 可能会改变
        }
    }
}
