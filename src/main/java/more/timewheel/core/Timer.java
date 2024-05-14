package more.timewheel.core;

import more.timewheel.task.Task;

public interface Timer extends AutoCloseable
{

    /**
     * 添加定时任务
     */
    void add(Task task);

    /**
     * 推进内部时钟
     *
     * @return 是否执行了任何任务
     */
    boolean advanceClock(long timeout) throws InterruptedException;

    /**
     * 获取待执行任务的数量
     */
    int size();
}
