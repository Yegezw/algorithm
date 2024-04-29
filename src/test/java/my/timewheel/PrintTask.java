package my.timewheel;

import lombok.extern.slf4j.Slf4j;
import my.timewheel.task.Task;

@Slf4j
public class PrintTask extends Task
{

    /**
     * 任务描述
     */
    private final String desc;

    public PrintTask(String desc, long delayMs)
    {
        this.desc    = desc;
        this.delayMs = delayMs;
    }

    @Override
    public void run()
    {
        log.info("任务 {} 执行", desc);
    }
}
