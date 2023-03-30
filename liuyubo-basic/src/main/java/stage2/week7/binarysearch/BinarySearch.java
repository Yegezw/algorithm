package stage2.week7.binarysearch;

/**
 * 二分查找法: O(logN)
 */
public class BinarySearch {

    private BinarySearch() {
    }

    /**
     * 二分查找法递归实现
     */
    public static <E extends Comparable<E>> int searchR(E[] data, E target) {
        return searchR(data, 0, data.length - 1, target);
    }

    private static <E extends Comparable<E>> int searchR(E[] data, int l, int r, E target) {
        if (l > r) return -1;

        int mid = l + (r - l) / 2;
        if (data[mid].compareTo(target) == 0) return mid;

        if (data[mid].compareTo(target) < 0) return searchR(data, mid + 1, r, target);
        return searchR(data, l, mid - 1, target);
    }

    /**
     * 二分查找法非递归实现
     */
    public static <E extends Comparable<E>> int search(E[] data, E target) {
        int l = 0;
        int r = data.length - 1;
        int mid;

        // 在 data[l, r] 中查找 target
        // 每次循环开始时: data[l]、data[r] 还没看, 因此 l == r 时, 依然要进入循环
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (data[mid].compareTo(target) == 0) return mid;
            if (data[mid].compareTo(target) < 0) l = mid + 1;
            else r = mid - 1;
        }

        return -1;
    }
}
