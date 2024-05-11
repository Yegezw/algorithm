package my.timewheel.core;

import my.timewheel.task.Task;

public interface Timer extends AutoCloseable
{

    /**
     * 添加一个新任务
     */
    void addTask(Task task);

    /**
     * 推动指针
     */
    void advanceClock(long timeout) throws InterruptedException;

    /**
     * 获取任务数量
     */
    int size();
}
