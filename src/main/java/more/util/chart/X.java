package more.util.chart;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/**
 * 标识 X 轴字段
 *
 * @see ChartFactory#rowListToChart(List, Class)
 * @see ChartFactory#rowListToChart(List, Class, ValuePostProcessor)
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface X
{
}
