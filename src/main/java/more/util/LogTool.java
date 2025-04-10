package more.util;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
public class LogTool
{

    private LogTool()
    {
    }

    private static final double base = 1_000_000_000.0;

    public static <T> T execute(Supplier<T> supplier, String msg)
    {
        Stopwatch stopwatch = Stopwatch.createStarted();
        T ret = supplier.get();
        long time = stopwatch.stop().elapsed(TimeUnit.NANOSECONDS);
        log.info("{}, 耗时 {} s", msg, time / base);
        return ret;
    }
}
