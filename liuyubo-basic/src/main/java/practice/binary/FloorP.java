package practice.binary;

/**
 * 练习 floor
 */
@SuppressWarnings("all")
public class FloorP {

    /**
     * 查找 < target 的最右边的索引
     */
    public static <E extends Comparable<E>> int lower(E[] arr, E target) {
        int mid;
        int l = -1;
        int r = arr.length - 1;

        while (l < r) {
            mid = l + (r - l + 1) / 2;
            if (arr[mid].compareTo(target) < 0) l = mid;
            else r = mid - 1;
        }

        return l;
    }


    /**
     * <p>存在 target 时, 返回 = target 的最左边的索引
     * <p>没有 target 时, 返回 < target 的最右边的索引
     */
    public static <E extends Comparable<E>> int floorL(E[] arr, E target) {
        int lower = lower(arr, target);
        if (lower + 1 < arr.length && arr[lower + 1].compareTo(target) == 0) return lower + 1;
        return lower;
    }

    /**
     * <p>存在 target 时, 返回 = target 的最右边的索引
     * <p>没有 target 时, 返回 < target 的最右边的索引
     */
    public static <E extends Comparable<E>> int floorR(E[] arr, E target) {
        int mid;
        int l = -1;
        int r = arr.length - 1;

        while (l < r) {
            mid = l + (r - l + 1) / 2;
            if (arr[mid].compareTo(target) <= 0) l = mid;
            else r = mid - 1;
        }

        return l;
    }

}
