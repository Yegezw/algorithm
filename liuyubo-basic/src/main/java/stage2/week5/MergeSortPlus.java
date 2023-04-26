package stage2.week5;

import stage1.week2.InsertionSort;

/**
 * <p>归并排序: O(N * logN)
 * <p>优化 1: merge 条件, 对完全有序的数组 O(n)
 * <p>优化 2: 数据量小的时候(<=16)采用插入排序
 * <p>优化 3: 避免频繁的在内存中开辟空间
 */
@SuppressWarnings("all")
public class MergeSortPlus {

    private MergeSortPlus() {
    }

    /**
     * 归并排序
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        E[] temp = (E[]) new Comparable[arr.length]; // 优化 3: 避免频繁的在内存中开辟空间
        sort(arr, 0, arr.length - 1, temp);
    }

    /**
     * 归并排序 arr[l, r]
     */
    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, E[] temp) {
        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r); // 优化 2: 数据量小的时候(<=16)采用插入排序
            return;
        }

        int mid = l + (r - l) / 2;
        sort(arr, l, mid, temp);       // arr[l, mid]
        sort(arr, mid + 1, r, temp); // arr[mid + 1, r]

        if (arr[mid].compareTo(arr[mid + 1]) > 0) merge(arr, l, mid, r, temp); // 优化 1: merge 条件
    }

    /**
     * 合并两个有序数组 arr[l, mid] 和 arr[mid + 1, r], 使得 arr[l, r] 整体有序
     */
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp) {
        System.arraycopy(arr, l, temp, l, r - l + 1);

        int p1 = l;       // temp[l, mid]
        int p2 = mid + 1; // temp[mid + 1, r]
        int i = l;        // arr[l, r]

        while (p1 <= mid && p2 <= r) arr[i++] = temp[p1].compareTo(temp[p2]) <= 0 ? temp[p1++] : temp[p2++];
        while (p1 <= mid) arr[i++] = temp[p1++];
        while (p2 <= r) arr[i++] = temp[p2++];
    }
}
