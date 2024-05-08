package stage2.week7.floor;

@SuppressWarnings("all")
public class Lower
{

    private Lower()
    {
    }

    /**
     * 查找 < target 的最大值所在的索引
     */
    public static <E extends Comparable<E>> int lower(E[] data, E target)
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
} 
