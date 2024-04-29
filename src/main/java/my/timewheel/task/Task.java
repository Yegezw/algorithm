package my.timewheel.task;

import lombok.Getter;

/**
 * 定时任务
 */
@Getter
public abstract class Task implements Runnable
{

    /**
     * 延迟时间 - 相对
     */
    protected long delayMs;
}
