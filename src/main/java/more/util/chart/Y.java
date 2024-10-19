package more.util.chart;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/**
 * 标识线中的点字段
 *
 * @see ChartFactory#rowListToChart(List, Class)
 * @see ChartFactory#rowListToChart(List, Class, ValuePostProcessor)
 * @see ChartFactory#lineListToChart(List, List, Class)
 * @see ChartFactory#lineListToChart(List, List, Class, ValuePostProcessor)
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Y
{

    /**
     * 线名称 OR 该点的 X 轴值
     */
    String description();

    /**
     * 字段为 null 时的默认值, UNDEFINED 则不做任何处理
     */
    String defaultValue() default ChartFactory.UNDEFINED;
}
