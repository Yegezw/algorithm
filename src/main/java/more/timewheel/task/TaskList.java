package more.timewheel.task;

import more.timewheel.core.SystemTimer;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * <p>定时任务 - 环形链表
 * <p>
 * 添加、删除、遍历都加了 synchronized, 禁止并发<br>
 * {@link #add(TaskEntry)} 和 {@link #remove(TaskEntry)} 对 TaskEntry 加锁, 禁止不同 TaskList 下的相同 TaskEntry 并发<br>
 * 取消任务 TaskList1.remove(entry) 和任务降级 TaskList2.add(entry) 禁止并发<br>
 * {@link TaskEntry#remove() 取消任务} TaskList1.remove(entry)<br>
 * {@link SystemTimer#advanceClock(long) 任务降级} TaskList1.remove(entry) -> TaskList2.add(entry)
 * <p/>
 */
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
     *
     * @see TimeWheel#add(TaskEntry)
     */
    public void add(TaskEntry entry)
    {
        boolean done = false;
        while (!done)
        {
            // 如果该 TaskEntry 已经被添加到其它 TaskList 中, 则移除
            // entry.remove() 有可能删除失败, 因此需要自旋删除, 直到 entry.list = null
            // 删除操作需要获取锁, 在下面的同步块之外执行此操作以避免死锁
            entry.remove(); // 在极少数情况下 entry 会删除失败

            synchronized (this)
            {
                synchronized (entry)
                {
                    if (entry.list == null)
                    {
                        entry.list = this;

                        TaskEntry tail = root.prev;

                        tail.next  = entry;
                        entry.prev = tail;

                        entry.next = root;
                        root.prev  = entry;

                        taskCounter.incrementAndGet(); // 添加任务个数

                        done = true;
                    }
                }
            }
        }
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
            if (!cur.cancelled())
            {
                consumer.accept(cur.task);
            }
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
            remove(cur);
            consumer.accept(cur);
            cur = root.next;
        }
        expiration.set(-1L);
    }

    /**
     * 从链表中移除 TaskEntry
     *
     * @see TaskEntry#remove() 取消任务
     * @see SystemTimer#advanceClock(long) 任务降级
     */
    public synchronized void remove(TaskEntry entry)
    {
        synchronized (entry)
        {
            if (entry.list == this)
            {
                TaskEntry prev = entry.prev;
                TaskEntry next = entry.next;

                prev.next = next;
                next.prev = prev;

                entry.prev = null;
                entry.next = null;
                entry.list = null;

                taskCounter.decrementAndGet(); // 减少任务个数
            }
        }
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
