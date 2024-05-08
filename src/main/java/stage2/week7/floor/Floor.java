package stage2.week7.floor;

@SuppressWarnings("all")
public class Floor
{

    private Floor()
    {
    }

    /**
     * 查找 < target 的最大值所在的索引
     */
    private static <E extends Comparable<E>> int lower(E[] data, E target)
    {
        int l = -1;
        int r = data.length - 1;
        int mid;

        // 在 data[l, r] 中查找 < target 的最右边的索引, l = -1
        // 每次循环开始时: data[l] 可能是解, data[r] 还没看, 因此当 l == r 时, l 就是解
        while (l < r)
        {
            mid = l + (r - l + 1) / 2; // l < mid <= r, l 与 r 相邻时 mid = r
            if (data[mid].compareTo(target) < 0) l = mid;
            else r = mid - 1;
        }

        return l;
    }

    /**
     * <p>存在 target 时, 返回 = target 的最左边的索引
     * <p>没有 target 时, 返回 < target 的最右边的索引
     */
    public static <E extends Comparable<E>> int floorL(E[] data, E target)
    {
        int lower = lower(data, target);
        if (lower + 1 < data.length && data[lower + 1].compareTo(target) == 0) return lower + 1;
        return lower;
    }

    /**
     * <p>存在 target 时, 返回 = target 的最右边的索引
     * <p>没有 target 时, 返回 < target 的最右边的索引
     */
    public static <E extends Comparable<E>> int floorR(E[] data, E target)
    {
        int l = -1;
        int r = data.length - 1;
        int mid;

        // 在 data[l, r] 中查找 < target 的最右边的索引, l = -1
        // 每次循环开始时: data[l] 可能是解, data[r] 还没看, 因此当 l == r 时, l 就是解
        while (l < r)
        {
            mid = l + (r - l + 1) / 2; // l < mid <= r, l 与 r 相邻时 mid = r
            if (data[mid].compareTo(target) <= 0) l = mid;
            else r = mid - 1;
        }

        return l;
    }
}
