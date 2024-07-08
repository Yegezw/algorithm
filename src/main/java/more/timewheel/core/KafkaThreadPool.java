package more.timewheel.core;

import java.util.concurrent.*;

public class KafkaThreadPool extends ThreadPoolExecutor
{

    /**
     * 等待线程池完成所有任务
     */
    private final CountDownLatch complete = new CountDownLatch(1);

    public KafkaThreadPool(int corePoolSize)
    {
        super(
                corePoolSize,
                corePoolSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()
        );
    }

    public KafkaThreadPool(int corePoolSize, ThreadFactory threadFactory)
    {
        super(
                corePoolSize,
                corePoolSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                threadFactory
        );
    }

    @Override
    public void shutdown()
    {
        super.shutdown();
        try
        {
            complete.await(); // 等待线程池完成所有任务
        }
        catch (InterruptedException ignore)
        {
        }
    }

    @Override
    protected void terminated()
    {
        super.terminated();
        complete.countDown();
    }
}
