package stage2.week5;

import stage1.week2.InsertionSort;

/**
 * 自底向上归并排序: O(N * logN)
 */
@SuppressWarnings("all")
public class MergeSortBU {
    
    private MergeSortBU() {
    }

    /**
     * 自底向上归并排序
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        int n = arr.length;
        E[] temp = (E[]) new Comparable[n];
        
        // 对 arr[0, n - 1] 中所有的 arr[i, i + 15], 使用插入排序法
        for (int i = 0; i < n; i += 16) {
            InsertionSort.sort(arr, i, Math.min(i + 15, n - 1));
        }
        
        // 遍历合并的区间长度(把 2 个区间合并成 1 个区间)
        for (int size = 16; size < n; size += size) {
            // 合并 arr[i, i + size - 1] 和 arr[i + size, i + size + size - 1]
            for (int i = 0; i + size < n; i += 2 * size) {
                if (arr[i + size - 1].compareTo(arr[i + size]) > 0) {
                    merge(arr, i, i + size - 1, Math.min(i + size + size - 1, n - 1), temp);
                }
            }
        }
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
