package stage2.week7.work;

public class BinarySearchWork {

    /**
     * 用 >= target 的最左边的索引的思路实现二分查找法
     */
    public static <E extends Comparable<E>> int search(E[] arr, E target) {
        int l = 0;
        int r = arr.length;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) >= 0) r = mid;
            else l = mid + 1;
        }

        // r 是 >= target 的最左边的索引, 注意 r 的取值范围是 [0, arr.length]
        // 如果 data[r] == target, 则返回 r, 否则返回 -1
        if (r < arr.length && arr[r].compareTo(target) == 0) return r;
        else return -1;
    }

}