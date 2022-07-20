package stage2.week7.ceil;

@SuppressWarnings("all")
public class Upper {

    private Upper() {
    }

    /**
     * 查找 > target 的最左边的索引
     */
    public static <E extends Comparable<E>> int upper(E[] data, E target) {
        int mid;
        int l = 0;
        int r = data.length;

        // 在 data[l, r] 中查找 > target 的最左边的索引, r = data.length
        // 每次循环开始时: data[l] 还没看, data[r] 可能是解, 因此当 l == r 时, r 就是解
        while (l < r) {
            mid = l + (r - l) / 2;
            if (data[mid].compareTo(target) > 0) r = mid;
            else l = mid + 1;
        }

        return r;
    }

}
