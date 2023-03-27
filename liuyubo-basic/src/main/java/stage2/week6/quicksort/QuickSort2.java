package stage2.week6.quicksort;

import stage1.week2.InsertionSort;

import java.util.Random;

/**
 * 双路快速排序: O(N * logN)
 */
@SuppressWarnings("all")
public class QuickSort2 {

    private static final Random RANDOM = new Random();

    private QuickSort2() {
    }

    /**
     * 快速排序
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序 arr[l, r]
     */
    public static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        int p = partition(arr, l, r);

        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    /**
     * 双路快排
     */
    public static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
        int p = RANDOM.nextInt(r - l + 1) + l;
        swap(arr, l, p);

        E v = arr[l];
        int p1 = l + 1;
        int p2 = r;

        // arr[l, p1 - 1] >= v, arr[p2 + 1, r] <= v
        // 循环结束的 2 种情况: p1 > p2 和 p1 == p2
        while (true) {
            while (p1 <= p2 && arr[p1].compareTo(v) < 0) p1++; // arr[p1] >= v
            while (p1 <= p2 && arr[p2].compareTo(v) > 0) p2--; // arr[p2] <= v

            if (p1 >= p2) break; // 也可以理解为当 p1 >= p2 时, 就没必要 swap(arr, p1, p2) 了

            swap(arr, p1++, p2--);
        }

        swap(arr, l, p2);
        return p2;
    }

    public static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }
}
