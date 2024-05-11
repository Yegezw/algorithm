package more.timewheel;

import more.timewheel.core.SystemTimer;
import more.timewheel.task.Task;

@SuppressWarnings("all")
public class TimeWheelTest
{

    public static void main(String[] args) throws InterruptedException
    {
        Task[] tasks = new Task[64];
        for (int i = 0; i < tasks.length; i++)
        {
            tasks[i] = new PrintTask(i + "", 1000 * i);
        }

        SystemTimer timer = new SystemTimer("test");
        for (Task task : tasks)
        {
            timer.addTask(task);
        }

        while (true)
        {
            Thread.sleep(500);
            if (timer.size() == 0)
            {
                timer.close();
                break;
            }
        }
    }
}
