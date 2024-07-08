package more.timewheel;

import more.timewheel.core.SystemTimer;
import more.timewheel.task.Task;

@SuppressWarnings("all")
public class TimeWheelTest
{

    public static void main(String[] args)
    {
        Task[] tasks = new Task[64];
        for (int i = 0; i < tasks.length; i++)
        {
            tasks[i] = new PrintTask(i + "", 1000 * i);
        }

        SystemTimer timer = new SystemTimer("test");
        for (Task task : tasks)
        {
            timer.add(task);
        }

        tasks[16].cancel();
        tasks[45].cancel();

        timer.close(); // 阻塞函数
    }
}
