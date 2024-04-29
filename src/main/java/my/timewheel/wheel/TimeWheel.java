package my.timewheel.wheel;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import my.timewheel.task.TaskEntry;
import my.timewheel.task.TaskList;

import java.util.concurrent.DelayQueue;

/**
 * 时间轮
 */
@Data
@Slf4j
public class TimeWheel
{

    /**
     * 基本时间跨度
     */
    private long tickMs;
    /**
     * 时间单位个数
     */
    private int  wheelSize;
    /**
     * 总体时间跨度
     */
    private long interval;
    /**
     * <p>当前所处时间 - 绝对 - tickMs 的倍数
     * <p>代表 [currentTime ... currentTime + tickMs) 已过期
     */
    private long currentTime;

    // ----------------------------------------

    /**
     * 定时任务列表, 间隔 [0 ... tickMs)
     */
    private TaskList[]           buckets;
    /**
     * 只有一个 DelayQueue, 协助推进时间轮
     */
    private DelayQueue<TaskList> delayQueue;

    // ----------------------------------------

    /**
     * 上层时间轮
     */
    private volatile TimeWheel overflowWheel;

    // =============================================================================

    public TimeWheel(long tickMs, int wheelSize, long startMs, DelayQueue<TaskList> delayQueue)
    {
        this.tickMs      = tickMs;
        this.wheelSize   = wheelSize;
        this.interval    = tickMs * wheelSize;
        this.currentTime = startMs - (startMs % tickMs);
        this.buckets     = new TaskList[wheelSize];
        this.delayQueue  = delayQueue;
        for (int i = 0; i < wheelSize; i++)
        {
            buckets[i] = new TaskList();
        }
    }

    /**
     * 获取上层时间轮
     */
    private TimeWheel getOverflowWheel()
    {
        if (overflowWheel == null)
        {
            overflowWheel = new TimeWheel(interval, wheelSize, currentTime, delayQueue);
        }
        return overflowWheel;
    }

    // =============================================================================

    public boolean addTaskEntry(TaskEntry entry)
    {
        // 过期时间 - 绝对
        // expiration in [currentTime + tickMs ... currentTime + interval)
        long expiration = entry.getExpireMs();

        if (expiration < currentTime + tickMs)
        {
            return false; // 定时任务到期 - 添加失败
        }
        else if (expiration < currentTime + interval)
        {
            long virtualId = (expiration / tickMs);
            int  index     = (int) (virtualId % wheelSize);

            TaskList bucket = buckets[index];
            bucket.addTaskEntry(entry);

            boolean first = bucket.setExpiration(virtualId * tickMs);
            if (first) delayQueue.offer(bucket); // 关键
            return true;
        }
        else
        {
            // 当前时间轮不能满足, 需要升级时间轮
            TimeWheel wheel = getOverflowWheel();
            return wheel.addTaskEntry(entry);
        }
    }

    /**
     * 推进指针
     */
    public void advanceClock(long timeMs)
    {
        if (timeMs >= currentTime + tickMs)
        {
            currentTime = timeMs - (timeMs % tickMs);
            if (overflowWheel != null)
            {
                getOverflowWheel().advanceClock(timeMs);
            }
        }
    }
}
