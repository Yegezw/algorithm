package my.timewheel.core;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaThread extends Thread
{

    public KafkaThread(final String name, Runnable runnable, boolean daemon)
    {
        super(runnable, name);
        configureThread(name, daemon);
    }

    private void configureThread(final String name, boolean daemon)
    {
        setDaemon(daemon);
        setUncaughtExceptionHandler(new UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException(Thread t, Throwable e)
            {
                log.error("Uncaught exception in thread '{}': ", name, e);
            }
        });
    }

    // =============================================================================

    public static KafkaThread daemon(final String name, Runnable runnable)
    {
        return new KafkaThread(name, runnable, true);
    }

    public static KafkaThread nonDaemon(final String name, Runnable runnable)
    {
        return new KafkaThread(name, runnable, false);
    }
}
