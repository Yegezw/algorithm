package practice.binary;

/**
 * 练习 ceil
 */
@SuppressWarnings("all")
public class CeilP {

    /**
     * 查找 > target 的最左边的索引
     */
    public static <E extends Comparable<E>> int upper(E[] arr, E target) {
        int mid;
        int l = 0;
        int r = arr.length;

        while (l < r) {
            mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) > 0) r = mid;
            else l = mid + 1;
        }

        return r;
    }

    /**
     * <p>存在 target 时, 返回 = target 的最右边的索引
     * <p>没有 target 时, 返回 > target 的最左边的索引
     */
    public static <E extends Comparable<E>> int ceilR(E[] arr, E target) {
        int upper = upper(arr, target);
        if (upper - 1 >= 0 && arr[upper - 1].compareTo(target) == 0) return upper - 1;
        return upper;
    }

    /**
     * <p>存在 target 时, 返回 = target 的最左边的索引
     * <p>没用 target 时, 返回 > target 的最左边的索引
     */
    public static <E extends Comparable<E>> int ceilL(E[] arr, E target) {
        int mid;
        int l = 0;
        int r = arr.length;

        while (l < r) {
            mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) >= 0) r = mid;
            else l = mid + 1;
        }

        return r;
    }

}
