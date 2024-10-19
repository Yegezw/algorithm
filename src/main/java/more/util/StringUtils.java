package more.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StringUtils
{

    private StringUtils()
    {
    }

    /**
     * 保留两位小数, null OR "" 不做处理
     */
    public static String retainTwoDecimalPlaces(Object obj)
    {
        if (obj == null) return null;
        if (obj instanceof String) return retainTwoDecimalPlaces((String) obj);
        else if (obj instanceof BigDecimal) return retainTwoDecimalPlaces((BigDecimal) obj);
        else throw new IllegalArgumentException();
    }

    /**
     * 保留两位小数, null OR "" 不做处理
     */
    public static String retainTwoDecimalPlaces(String str)
    {
        if (str == null || str.isEmpty()) return str;
        return retainTwoDecimalPlaces(new BigDecimal(str));
    }

    /**
     * 保留两位小数, null 不做处理
     */
    public static String retainTwoDecimalPlaces(BigDecimal bd)
    {
        if (bd == null) return null;
        bd = bd.setScale(2, RoundingMode.HALF_UP); // 设置小数点后两位并四舍五入
        return bd.toString();
    }
}
