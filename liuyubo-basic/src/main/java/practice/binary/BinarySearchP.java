package practice.binary;

/**
 * 练习二分查找法
 */
@SuppressWarnings("all")
public class BinarySearchP {

    /**
     * 非递归
     */
    public static <E extends Comparable<E>> int search(E[] arr, E target) {
        int mid;
        int l = 0;
        int r = arr.length - 1;

        while (l <= r) {
            mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) == 0) return mid;
            if (arr[mid].compareTo(target) > 0) r = mid - 1;
            else l = mid + 1;
        }

        return -1;
    }

    /**
     * 递归
     */
    public static <E extends Comparable<E>> int searchR(E[] arr, E target) {
        return searchR(arr, 0, arr.length - 1, target);
    }

    public static <E extends Comparable<E>> int searchR(E[] arr, int l, int r, E target) {
        if (l > r) return -1;

        int mid = l + (r - l) / 2;
        if (arr[mid].compareTo(target) == 0) return mid;

        if (arr[mid].compareTo(target) > 0) return searchR(arr, l, mid - 1, target);
        return searchR(arr, mid + 1, r, target);
    }

}
