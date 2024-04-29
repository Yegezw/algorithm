package my.timewheel.core;

import my.timewheel.task.Task;

public interface Timer
{

    /**
     * 添加一个新任务
     */
    void addTask(Task task);

    /**
     * 推动指针
     */
    void advanceClock(long timeout);

    /**
     * 关闭服务, 剩下的无法被执行
     */
    void shutdown();
}
