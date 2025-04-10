package more.util;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Pool
{

    private Pool()
    {
    }

    public static int core = Runtime.getRuntime().availableProcessors();

    public static ThreadPoolExecutor commonPool = new ThreadPoolExecutor(
            core, core * 5, 60, TimeUnit.SECONDS, 
            new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy()
    );
}
