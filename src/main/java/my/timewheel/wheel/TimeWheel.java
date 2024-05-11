package my.timewheel.wheel;

import lombok.extern.slf4j.Slf4j;
import my.timewheel.task.TaskEntry;
import my.timewheel.task.TaskList;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 时间轮
 */
@Slf4j
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
     * <p>代表 [currentTime ... currentTime + tickMs) 已过期
     * <p>当前时间轮所能处理的时间段为 [currentTime ... currentTime + interval)
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
     * 上层时间轮
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
    private void addOverflowWheel()
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

    public boolean addTaskEntry(TaskEntry entry)
    {
        // 过期时间 - 绝对
        // expiration in [currentTime + tickMs ... currentTime + interval)
        long expiration = entry.expirationMs;

        if (expiration < currentTimeMs + tickMs)
        {
            return false; // 定时任务到期 - 添加失败
        }
        else if (expiration < currentTimeMs + interval)
        {
            long virtualId = (expiration / tickMs);
            int  bucketId  = (int) (virtualId % (long) wheelSize);

            TaskList bucket = buckets[bucketId];
            bucket.addTaskEntry(entry);

            // 如果 bucket 的过期时间已更新, 则需要加入 delayQueue
            boolean updated = bucket.setExpiration(virtualId * tickMs);
            if (updated)
            {
                delayQueue.offer(bucket); // 关键
            }

            return true;
        }
        else
        {
            // 超出 interval, 放入上层时间轮
            if (overflowWheel == null)
            {
                addOverflowWheel();
            }
            return overflowWheel.addTaskEntry(entry);
        }
    }

    /**
     * 推进 currentTimeMs
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
