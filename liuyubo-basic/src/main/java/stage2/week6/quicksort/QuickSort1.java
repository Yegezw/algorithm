package stage2.week6.quicksort;

import stage1.week2.InsertionSort;

import java.util.Random;

/**
 * 快速排序: 当数组中的元素一致时退化
 */
@SuppressWarnings("all")
public class QuickSort1 {

    private static final Random RANDOM = new Random();

    private QuickSort1() {
    }

    /**
     * 普通快速排序
     */
    public static <E extends Comparable<E>> void sort1(E[] arr) {
        process1(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void process1(E[] arr, int l, int r) {
        if (l >= r) return;

        int p = partition(arr, l, r);

        process1(arr, l, p - 1);
        process1(arr, p + 1, r);
    }

    /**
     * 插入排序优化后的快速排序
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        process2(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void process2(E[] arr, int l, int r) {
        if (r - l <= 7) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        int p = partition(arr, l, r);

        process2(arr, l, p - 1);
        process2(arr, p + 1, r);
    }

    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
        int p = RANDOM.nextInt(r - l + 1) + l;
        swap(arr, l, p);

        E v = arr[l];
        int j = l;

        // arr[l + 1, j] < v, arr[j + 1, i - 1] >= v
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(v) < 0) swap(arr, i, ++j);
        }

        swap(arr, l, j);
        return j;
    }

    private static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

}
