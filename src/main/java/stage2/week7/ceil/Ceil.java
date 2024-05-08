package stage2.week7.ceil;

@SuppressWarnings("all")
public class Ceil
{

    private Ceil()
    {
    }

    /**
     * 在 data[] 中查找 > target 的最小值所在的索引
     */
    private static <E extends Comparable<E>> int upper(E[] data, E target)
    {
        int l = 0;
        int r = data.length;
        int mid;

        // 在 data[l, r] 中查找 > target 的最左边的索引, r = data.length
        // 每次循环开始时: data[l] 还没看, data[r] 可能是解, 因此当 l == r 时, r 就是解
        while (l < r)
        {
            mid = l + (r - l) / 2; // l <= mid < r, l 与 r 相邻时 mid = l
            if (data[mid].compareTo(target) > 0) r = mid;
            else l = mid + 1;
        }

        return r;
    }

    /**
     * <p>存在 target 时, 返回 = target 的最右边的索引
     * <p>没有 target 时, 返回 > target 的最左边的索引
     */
    public static <E extends Comparable<E>> int ceilR(E[] data, E target)
    {
        int upper = upper(data, target);
        if (upper - 1 >= 0 && data[upper - 1].compareTo(target) == 0) return upper - 1;
        return upper;
    }

    /**
     * <p>存在 target 时, 返回 = target 的最左边的索引
     * <p>没有 target 时, 返回 > target 的最左边的索引
     */
    public static <E extends Comparable<E>> int ceilL(E[] data, E target)
    {
        int l = 0;
        int r = data.length;
        int mid;

        // 在 data[l, r] 中查找 >= target 的最左边的索引, r = data.length
        // 每次循环开始时: data[l] 还没看, data[r] 可能是解, 因此当 l == r 时, r 就是解
        while (l < r)
        {
            mid = l + (r - l) / 2; // l <= mid < r, l 与 r 相邻时 mid = l
            if (data[mid].compareTo(target) >= 0) r = mid;
            else l = mid + 1;
        }

        return r;
    }
}
