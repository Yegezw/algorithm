package stage2.week5;

import java.util.Arrays;

/**
 * 归并排序: O(N * logN)
 */
@SuppressWarnings("all")
public class MergeSort
{

    private MergeSort()
    {
    }

    /**
     * 归并排序
     */
    public static <E extends Comparable<E>> void sort(E[] arr)
    {
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 归并排序 arr[l, r]
     */
    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r)
    {
        if (l >= r) return;

        int mid = l + (r - l) / 2;
        sort(arr, l, mid);       // arr[l, mid]
        sort(arr, mid + 1, r); // arr[mid + 1, r]

        merge(arr, l, mid, r);
    }

    /**
     * 合并两个有序数组 arr[l, mid] 和 arr[mid + 1, r], 使得 arr[l, r] 整体有序
     */
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r)
    {
        E[] temp = Arrays.copyOfRange(arr, l, r + 1);

        int p1 = 0;           // temp[0, mid - l]
        int p2 = mid + 1 - l; // temp[mid + 1 - l, r - l]
        int i  = l;           // arr[l, r]

        while (p1 <= mid - l && p2 <= r - l) arr[i++] = temp[p1].compareTo(temp[p2]) <= 0 ? temp[p1++] : temp[p2++];
        while (p1 <= mid - l) arr[i++] = temp[p1++];
        while (p2 <= r - l) arr[i++] = temp[p2++];
    }
}
