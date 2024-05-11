package more.timewheel.task;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * 定时任务 - 环形链表
 */
@Slf4j
public class TaskList implements Delayed
{

    /**
     * <p>过期时间 - 绝对 - tickMs 的倍数
     * <p>该 bucket 下的所有 TaskEntry.expirationMs in [expiration ... expiration + tickMs)
     */
    private final AtomicLong expiration;

    // ----------------------------------------

    /**
     * 虚拟头节点
     */
    private final TaskEntry     root;
    /**
     * 当前 TaskList 所属 TimeWheel 中的任务数量
     */
    private final AtomicInteger taskCounter;

    // =============================================================================

    public TaskList(AtomicInteger taskCounter)
    {
        this.expiration  = new AtomicLong(-1L);
        this.root        = new TaskEntry(null, -1L);
        this.taskCounter = taskCounter;

        root.next = root;
        root.prev = root;
    }

    // ----------------------------------------

    /**
     * 获取过期时间
     */
    public long getExpiration()
    {
        return expiration.get();
    }

    /**
     * 更新过期时间, 更新成功返回 true
     */
    public boolean setExpiration(long expirationMs)
    {
        return expiration.getAndSet(expirationMs) != expirationMs;
    }

    // =============================================================================

    /**
     * 向链表中添加 TaskEntry
     */
    public synchronized void addTaskEntry(TaskEntry entry)
    {
        TaskEntry tail = root.prev;

        tail.next  = entry;
        entry.prev = tail;

        entry.next = root;
        root.prev  = entry;

        taskCounter.incrementAndGet();
    }

    // ----------------------------------------

    /**
     * 遍历链表中的所有 TaskEntry.Task, 调用 consumer.accept(task)
     */
    public synchronized void foreach(Consumer<Task> consumer)
    {
        TaskEntry cur = root.next;
        while (cur != root)
        {
            TaskEntry next = cur.next;
            consumer.accept(cur.task);
            cur = next;
        }
    }

    /**
     * <p>清空链表中的所有 TaskEntry
     * <p>对于每个 entry, 移除后都会调用 consumer.accept(entry)
     */
    public synchronized void flush(Consumer<TaskEntry> consumer)
    {
        TaskEntry cur = root.next;
        while (cur != root)
        {
            removeTaskEntry(cur);
            consumer.accept(cur);
            cur = root.next;
        }
        expiration.set(-1L);
    }

    /**
     * 从链表中移除 TaskEntry
     */
    public synchronized void removeTaskEntry(TaskEntry entry)
    {
        TaskEntry prev = entry.prev;
        TaskEntry next = entry.next;

        prev.next = next;
        next.prev = prev;

        entry.prev = null;
        entry.next = null;

        taskCounter.decrementAndGet();
    }

    // =============================================================================

    /**
     * 获取延迟时间
     */
    @Override
    public long getDelay(TimeUnit unit)
    {
        long diff = Math.max(getExpiration() - System.currentTimeMillis(), 0);
        return unit.convert(diff, TimeUnit.MICROSECONDS);
    }

    /**
     * 最小堆
     */
    @Override
    public int compareTo(Delayed o)
    {
        if (!(o instanceof TaskList))
        {
            throw new IllegalArgumentException();
        }

        TaskList another = (TaskList) o;
        return Long.compare(this.expiration.get(), another.expiration.get());
    }
}
