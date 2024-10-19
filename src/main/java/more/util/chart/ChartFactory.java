package more.util.chart;

import more.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 图表工厂
 */
public class ChartFactory
{

    /**
     * 默认处理器: 如果是默认值则直接返回, 否则会对值保留 2 位小数, 结果以 .00 结尾则截断
     */
    public static ValuePostProcessor DEFAULT_PROCESSOR = (value, isDefault) ->
    {
        if (isDefault) return (String) value;
        if (value == null) return null;
        String valueStr = StringUtils.retainTwoDecimalPlaces(value);
        valueStr = valueStr.replaceAll("\\.00$", "");
        return valueStr;
    };

    private ChartFactory()
    {
    }

    /**
     * @see Y#defaultValue()
     */
    @SuppressWarnings("all")
    public static final String UNDEFINED = "8D1CuDwkcG1kqQnwpJRMR7MJ9e73";

    private static final Class<X> CLAZZ_X = X.class;
    private static final Class<Y> CLAZZ_Y = Y.class;

    private static final ConcurrentHashMap<Class<?>, Field>            CACHE_CLAZZ_TO_FIELD_X      = new ConcurrentHashMap<>(32);
    private static final ConcurrentHashMap<Class<?>, ArrayList<Field>> CACHE_CLAZZ_TO_FIELD_Y_LIST = new ConcurrentHashMap<>(32);

    private static String postProcessValue(Field fieldY, Object data) throws IllegalAccessException
    {
        Object value = fieldY.get(data);
        if (value != null) return value.toString();
        String defaultValue = fieldY.getAnnotation(CLAZZ_Y).defaultValue();
        return defaultValue.equals(UNDEFINED) ? null : defaultValue;
    }

    private static String postProcessValue(Field fieldY, Object data,
                                           ValuePostProcessor processor) throws IllegalAccessException
    {
        if (processor == null) return postProcessValue(fieldY, data);

        Object value        = fieldY.get(data);
        String defaultValue = fieldY.getAnnotation(CLAZZ_Y).defaultValue();

        String result;
        if (value == null && !defaultValue.equals(UNDEFINED))
        {
            result = processor.postProcessValue(defaultValue, true);
        }
        else
        {
            result = processor.postProcessValue(value, false);
        }

        return result;
    }

    /**
     * T 必须满足<br>
     * ——T 不能为 null<br>
     * ——@X() 修饰的字段必须存在一个, 且该字段的值不可为空<br>
     * ——@Y(description = "") 修饰的字段, 可以有多个
     *
     * <h3>使用案例</h3>
     * <pre>
     * ------------------------------------------------------------------> Y
     * |   @X    | @Y(line01) | @Y(line02) | @Y(line03) | @Y(line04) |
     * ---------------------------------------------------------------
     * |  MONTH  |  VALUE_01  |  VALUE_02  |  VALUE_03  |  VALUE_04  |
     * ---------------------------------------------------------------
     * | 2023-10 |            |   165.00   |   060.00   |   060.00   |
     * ---------------------------------------------------------------
     * | 2023-11 |   115.00   |   085.00   |            |   070.00   |
     * ---------------------------------------------------------------
     * | 2023-12 |   125.00   |   105.00   |            |   080.00   |
     * ---------------------------------------------------------------
     * | 2024-01 |   135.00   |            |   090.00   |   090.00   |
     * ---------------------------------------------------------------
     * | 2024-02 |   180.00   |            |   170.00   |   020.00   |
     * ---------------------------------------------------------------
     * | 2024-03 |   050.00   |            |   160.00   |   130.00   |
     * ---------------------------------------------------------------
     * | 2024-04 |   100.00   |            |   150.00   |   080.00   |
     * ---------------------------------------------------------------
     * | 2024-05 |            |   100.00   |   140.00   |   070.00   |
     * ---------------------------------------------------------------
     * | 2024-06 |            |   050.00   |   130.00   |   060.00   |
     * ---------------------------------------------------------------
     * | 2024-07 |   260.00   |   020.00   |   120.00   |   050.00   |
     * ---------------------------------------------------------------
     * | 2024-08 |   260.00   |   500.00   |   110.00   |   180.00   |
     * ---------------------------------------------------------------
     * | 2024-09 |   050.00   |   160.00   |            |   190.00   |
     * ---------------------------------------------------------------
     * |
     * V
     * X
     * </pre>
     *
     * @param rowList 行数据
     * @param clazz   数据的 class, 空则抛出 NullPointerException
     * @return 图表
     * @see X
     * @see Y
     */
    public static <T> Chart rowListToChart(List<T> rowList, Class<T> clazz)
    {
        return rowListToChart(rowList, clazz, null);
    }

    /**
     * T 必须满足<br>
     * ——T 不能为 null<br>
     * ——@X() 修饰的字段必须存在一个, 且该字段的值不可为空<br>
     * ——@Y(description = "") 修饰的字段, 可以有多个
     *
     * <h3>使用案例</h3>
     * <pre>
     * ------------------------------------------------------------------> Y
     * |   @X    | @Y(line01) | @Y(line02) | @Y(line03) | @Y(line04) |
     * ---------------------------------------------------------------
     * |  MONTH  |  VALUE_01  |  VALUE_02  |  VALUE_03  |  VALUE_04  |
     * ---------------------------------------------------------------
     * | 2023-10 |            |   165.00   |   060.00   |   060.00   |
     * ---------------------------------------------------------------
     * | 2023-11 |   115.00   |   085.00   |            |   070.00   |
     * ---------------------------------------------------------------
     * | 2023-12 |   125.00   |   105.00   |            |   080.00   |
     * ---------------------------------------------------------------
     * | 2024-01 |   135.00   |            |   090.00   |   090.00   |
     * ---------------------------------------------------------------
     * | 2024-02 |   180.00   |            |   170.00   |   020.00   |
     * ---------------------------------------------------------------
     * | 2024-03 |   050.00   |            |   160.00   |   130.00   |
     * ---------------------------------------------------------------
     * | 2024-04 |   100.00   |            |   150.00   |   080.00   |
     * ---------------------------------------------------------------
     * | 2024-05 |            |   100.00   |   140.00   |   070.00   |
     * ---------------------------------------------------------------
     * | 2024-06 |            |   050.00   |   130.00   |   060.00   |
     * ---------------------------------------------------------------
     * | 2024-07 |   260.00   |   020.00   |   120.00   |   050.00   |
     * ---------------------------------------------------------------
     * | 2024-08 |   260.00   |   500.00   |   110.00   |   180.00   |
     * ---------------------------------------------------------------
     * | 2024-09 |   050.00   |   160.00   |            |   190.00   |
     * ---------------------------------------------------------------
     * |
     * V
     * X
     * </pre>
     *
     * @param rowList   行数据
     * @param clazz     数据的 class, 空则抛出 NullPointerException
     * @param processor @Y 标注的 Field 值后置处理器
     * @return 图表
     * @see X
     * @see Y
     */
    public static <T> Chart rowListToChart(List<T> rowList, Class<T> clazz,
                                           ValuePostProcessor processor)
    {
        if (clazz == null)
        {
            throw new NullPointerException("clazz must be not null");
        }
        if (rowList == null || rowList.isEmpty()) return new Chart();

        // 缓存已知属性
        Field            fieldX = CACHE_CLAZZ_TO_FIELD_X.get(clazz);
        ArrayList<Field> fieldYList;
        if (fieldX != null)
        {
            fieldYList = CACHE_CLAZZ_TO_FIELD_Y_LIST.get(clazz);
        }
        else
        {
            fieldYList = new ArrayList<>(5);
            for (Field field : clazz.getDeclaredFields())
            {
                if (field.isAnnotationPresent(CLAZZ_X))
                {
                    if (fieldX != null)
                    {
                        throw new IllegalArgumentException("Attributes modified by @X must have only one");
                    }
                    field.setAccessible(true);
                    fieldX = field;
                }
                else if (field.isAnnotationPresent(CLAZZ_Y))
                {
                    field.setAccessible(true);
                    fieldYList.add(field);
                }
            }
            if (fieldX == null || fieldYList.isEmpty())
            {
                throw new IllegalArgumentException("Please read the documentation for this method");
            }
            fieldYList.trimToSize();
            CACHE_CLAZZ_TO_FIELD_X.put(clazz, fieldX);
            CACHE_CLAZZ_TO_FIELD_Y_LIST.put(clazz, fieldYList);
        }

        final Chart chart = new Chart(rowList.size(), fieldYList.size());
        fillChartByRowList(rowList, chart, fieldX, fieldYList, processor);
        return chart;
    }

    private static <T> void fillChartByRowList(List<T> rowList, Chart chart,
                                               Field fieldX, List<Field> fieldYList,
                                               ValuePostProcessor processor)
    {
        final List<String>              xList   = chart.getXList();
        final Map<String, List<String>> lineMap = chart.getLineMap();

        try
        {
            for (T data : rowList)
            {
                if (data == null)
                {
                    throw new NullPointerException("rowList must be not contains null element");
                }
                Object x = fieldX.get(data);
                if (x == null) throw new NullPointerException();
                xList.add(fieldX.get(data).toString());

                for (Field fieldY : fieldYList)
                {
                    String       name = fieldY.getAnnotation(CLAZZ_Y).description();
                    List<String> line;
                    if (lineMap.containsKey(name)) line = lineMap.get(name);
                    else
                    {
                        line = new ArrayList<>(rowList.size());
                        lineMap.put(name, line);
                    }

                    line.add(postProcessValue(fieldY, data, processor));
                }
            }
        }
        catch (IllegalAccessException ignore)
        {
            // setAccessible(true) -> ignore
        }
    }

    /**
     * T 必须满足<br>
     * ——@Y(description = "") 修饰的字段, 可以有多个
     * <h3>使用案例</h3>
     * <pre>
     * Y
     * Λ
     * |
     * -------------------------------------------
     * | @Y(2024-01) | @Y(2024-02) | @Y(2024-03) |
     * -------------------------------------------
     * |   VALUE_1   |   VALUE_2   |   VALUE_3   |
     * -------------------------------------------
     * |      1      |      3      |      5      | lineName1
     * -------------------------------------------
     * |      9      |      5      |      3      | lineName2
     * --------------------------------------------------------> X
     * </pre>
     *
     * @param lineList 线数据列表
     * @param lineName 每条线数据所代表的线名称
     * @param clazz    数据的 class, 空则抛出 NullPointerException
     * @return 图表
     * @see X
     * @see Y
     */
    public static <T> Chart lineListToChart(List<T> lineList, List<String> lineName, Class<T> clazz)
    {
        return lineListToChart(lineList, lineName, clazz, null);
    }

    /**
     * T 必须满足<br>
     * ——@Y(description = "") 修饰的字段, 可以有多个
     * <h3>使用案例</h3>
     * <pre>
     * Y
     * Λ
     * |
     * -------------------------------------------
     * | @Y(2024-01) | @Y(2024-02) | @Y(2024-03) |
     * -------------------------------------------
     * |   VALUE_1   |   VALUE_2   |   VALUE_3   |
     * -------------------------------------------
     * |      1      |      3      |      5      | lineName1
     * -------------------------------------------
     * |      9      |      5      |      3      | lineName2
     * --------------------------------------------------------> X
     * </pre>
     *
     * @param lineList  线数据列表
     * @param lineName  每条线数据所代表的线名称
     * @param clazz     数据的 class, 空则抛出 NullPointerException
     * @param processor @Y 标注的 Field 值后置处理器
     * @return 图表
     * @see X
     * @see Y
     */
    public static <T> Chart lineListToChart(List<T> lineList, List<String> lineName, Class<T> clazz,
                                            ValuePostProcessor processor)
    {
        if (clazz == null)
        {
            throw new NullPointerException("clazz must be not null");
        }
        if (lineName == null || lineName.isEmpty()) return new Chart();
        final List<Field> fieldYList = getFieldYList(clazz);

        final Chart chart = new Chart(fieldYList.size(), lineName.size());
        if (lineList == null || lineList.isEmpty()) fillChartByLineName(chart, lineName, fieldYList);
        else fillChartByLineList(chart, lineList, lineName, fieldYList, processor);
        return chart;
    }

    private static List<Field> getFieldYList(Class<?> clazz)
    {
        // 缓存已知属性
        if (CACHE_CLAZZ_TO_FIELD_Y_LIST.containsKey(clazz))
        {
            return CACHE_CLAZZ_TO_FIELD_Y_LIST.get(clazz);
        }

        final ArrayList<Field> fieldYList = new ArrayList<>(16);
        for (Field field : clazz.getDeclaredFields())
        {
            if (field.isAnnotationPresent(CLAZZ_Y))
            {
                field.setAccessible(true);
                fieldYList.add(field);
            }
        }
        if (fieldYList.isEmpty())
        {
            throw new IllegalArgumentException("Please read the documentation for this method");
        }

        fieldYList.trimToSize();
        CACHE_CLAZZ_TO_FIELD_Y_LIST.put(clazz, fieldYList);
        return fieldYList;
    }

    private static void fillChartByLineName(Chart chart, List<String> lineName, List<Field> fieldYList)
    {
        final List<String>              xList   = chart.getXList();
        final Map<String, List<String>> lineMap = chart.getLineMap();
        for (Field fieldY : fieldYList) xList.add(fieldY.getAnnotation(CLAZZ_Y).description());
        for (String name : lineName) lineMap.put(name, null);
    }

    private static <T> void fillChartByLineList(Chart chart,
                                                List<T> lineList, List<String> lineName,
                                                List<Field> fieldYList, ValuePostProcessor processor)
    {
        if (lineList.size() != lineName.size())
        {
            throw new IllegalArgumentException("lineList.size != lineName.size");
        }
        final List<String>              xList   = chart.getXList();
        final Map<String, List<String>> lineMap = chart.getLineMap();

        for (Field fieldY : fieldYList) xList.add(fieldY.getAnnotation(CLAZZ_Y).description());
        try
        {
            for (int i = 0; i < lineList.size(); i++)
            {
                String name = lineName.get(i);
                T      data = lineList.get(i);
                if (data == null)
                {
                    lineMap.put(name, null);
                    continue;
                }

                List<String> line = new ArrayList<>(fieldYList.size());
                for (Field fieldY : fieldYList)
                {
                    line.add(postProcessValue(fieldY, data, processor));
                }

                lineMap.put(name, line);
            }
        }
        catch (IllegalAccessException ignore)
        {
            // setAccessible(true) -> ignore
        }
    }
}
