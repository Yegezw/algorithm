package more.timewheel.task;

import more.timewheel.core.SystemTimer;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 该类不是线程安全的<br>
 * 多线程同时调用 {@link #add(TaskEntry)} 是线程安全的<br>
 * 多线程同时调用 {@link #advanceClock(long)} 不是线程安全的<br>
 * 单线程调用 {@link #advanceClock(long)} 时, 不应该有任何 {@link #add(TaskEntry)} 调用
 */
public class TimeWheel
{

    /**
     * 该层时间轮的创建时间
     */
    @SuppressWarnings("all")
    private final long startMs;

    // ----------------------------------------

    /**
     * 基本时间跨度
     */
    private final long tickMs;
    /**
     * 时间单位个数
     */
    private final int  wheelSize;
    /**
     * 总体时间跨度
     */
    private final long interval;
    /**
     * <p>当前所处时间 - 绝对 - tickMs 的倍数
     * <p>
     * [currentTimeMs ... currentTimeMs + tickMs) 已过期<br>
     * 当前时间轮所能处理的时间段为 [currentTimeMs ... currentTimeMs + interval)
     * </p>
     * <p>
     * 不需要被 volatile 修饰<br>
     * 因为 SystemTimer 中调用 {@link #add(TaskEntry)} 读和调用 {@link #advanceClock(long)} 写都加了锁
     * </p>
     */
    private       long currentTimeMs;

    // ----------------------------------------

    /**
     * 定时任务列表, 间隔 [0 ... tickMs)
     */
    private final TaskList[]           buckets;
    /**
     * 只有一个 DelayQueue, 协助推进时间轮
     */
    private final DelayQueue<TaskList> delayQueue;
    /**
     * 当前 TimeWheel 中的任务数量
     */
    private final AtomicInteger        taskCounter;

    // ----------------------------------------

    /**
     * <p>上层时间轮
     * <p>overflowWheel 有可能被两个并发线程通过 add() 更新和读取
     */
    private volatile TimeWheel overflowWheel = null;

    // =============================================================================

    public TimeWheel(long tickMs, int wheelSize, long startMs, AtomicInteger taskCounter, DelayQueue<TaskList> delayQueue)
    {
        this.startMs       = startMs;
        this.tickMs        = tickMs;
        this.wheelSize     = wheelSize;
        this.interval      = tickMs * wheelSize;
        this.currentTimeMs = startMs - (startMs % tickMs);
        this.buckets       = new TaskList[wheelSize];
        this.delayQueue    = delayQueue;
        this.taskCounter   = taskCounter;

        for (int i = 0; i < wheelSize; i++)
        {
            buckets[i] = new TaskList(taskCounter);
        }
    }

    /**
     * 添加上层时间轮
     */
    private synchronized void addOverflowWheel()
    {
        if (overflowWheel == null)
        {
            overflowWheel = new TimeWheel(
                    interval,
                    wheelSize,
                    currentTimeMs,
                    taskCounter,
                    delayQueue
            );
        }
    }

    // =============================================================================

    /**
     * 该方法会对 currentTimeMs 进行读操作<br>
     * 该方法会对 overflowWheel 进行写操作
     *
     * @return true 代表定时任务添加成功、false 代表定时任务添加失败(已取消 OR 已到期)
     * @see SystemTimer#add(Task) 创建任务
     * @see SystemTimer#advanceClock(long) 任务降级
     */
    public boolean add(TaskEntry entry)
    {
        // 过期时间 - 绝对
        // expiration in [currentTimeMs + tickMs ... currentTimeMs + interval)
        long expiration = entry.expirationMs;

        if (entry.cancelled())
        {
            return false; // 定时任务已取消 - 添加失败
        }
        if (expiration < currentTimeMs + tickMs)
        {
            return false; // 定时任务已到期 - 添加失败
        }
        else if (expiration < currentTimeMs + interval)
        {
            long virtualId = (expiration / tickMs);
            int  bucketId  = (int) (virtualId % (long) wheelSize);

            TaskList bucket = buckets[bucketId];
            bucket.add(entry);

            // 如果 bucket 的过期时间已更新, 则将该 bucket 加入 delayQueue
            // 更新成功: TimeWheel.currentTimeMs 已推进, 之前的 bucket 已被使用
            // 更新失败: TimeWheel.currentTimeMs 未推进, 之前的 bucket 未被使用
            boolean updated = bucket.setExpiration(virtualId * tickMs);
            if (updated)
            {
                delayQueue.offer(bucket); // 关键
            }

            return true;
        }
        else
        {
            // expiration >= currentTimeMs + interval
            // 任务的到期时间 "超出" 当前时间轮所表示的时间范围时, 放入上层时间轮
            // 多线程可以同时调用 add() 对 overflowWheel 进行更新和读取
            // 当线程 A 更新 overflowWheel 后, 为了确保线程 B 读操作的可见性, overflowWheel 需要被 volatile 修饰
            if (overflowWheel == null)
            {
                addOverflowWheel();
            }
            return overflowWheel.add(entry);
        }
    }

    /**
     * <p>推进 currentTimeMs
     * <p>
     * 该方法会对 currentTimeMs 进行写操作<br>
     * 该方法会对 overflowWheel 进行读操作
     * </p>
     */
    public void advanceClock(long timeMs)
    {
        if (timeMs >= currentTimeMs + tickMs)
        {
            currentTimeMs = timeMs - (timeMs % tickMs);
            if (overflowWheel != null)
            {
                overflowWheel.advanceClock(currentTimeMs);
            }
        }
    }
}
