package my.timewheel.task;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * 定时任务 - 环形链表
 */
@Data
@Slf4j
public class TaskList implements Delayed
{

    /**
     * 虚拟头节点
     */
    private TaskEntry  root       = new TaskEntry(null, -1);
    /**
     * 过期时间 - 绝对 - tickMs 的倍数, [expiration ... expiration + tickMs)
     */
    private AtomicLong expiration = new AtomicLong(-1L);

    // =============================================================================

    public TaskList()
    {
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
    public void addTaskEntry(TaskEntry entry)
    {
        TaskEntry tail = root.prev;

        tail.next  = entry;
        entry.prev = tail;

        entry.next = root;
        root.prev  = entry;
    }

    // ----------------------------------------

    /**
     * <p>清空链表中的所有 TaskEntry
     * <p>对于每个 entry, 移除后都会调用 consumer.accept(entry)
     */
    public void clear(Consumer<TaskEntry> consumer)
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
    private void removeTaskEntry(TaskEntry entry)
    {
        TaskEntry prev = entry.prev;
        TaskEntry next = entry.next;

        prev.next = next;
        next.prev = prev;

        entry.prev = null;
        entry.next = null;
    }

    // =============================================================================

    /**
     * 获取延迟时间
     */
    @Override
    public long getDelay(TimeUnit unit)
    {
        long diff = expiration.get() - System.currentTimeMillis();
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
