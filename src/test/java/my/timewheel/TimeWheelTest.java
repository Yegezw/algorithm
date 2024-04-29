package my.timewheel;

import my.timewheel.core.TimerLauncher;
import my.timewheel.task.Task;

import java.util.concurrent.locks.LockSupport;

public class TimeWheelTest
{

    public static void main(String[] args)
    {
        Task[] tasks = new Task[64];
        for (int i = 0; i < tasks.length; i++)
        {
            tasks[i] = new PrintTask(i + 1 + "", 1000 * (i + 1));
        }

        TimerLauncher launcher = new TimerLauncher();
        for (Task task : tasks)
        {
            launcher.addTask(task);
        }

        LockSupport.park();
    }
}
