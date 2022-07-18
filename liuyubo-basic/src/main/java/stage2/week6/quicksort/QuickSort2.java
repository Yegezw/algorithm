package stage2.week6.quicksort;

import stage1.week2.InsertionSort;

import java.util.Random;

/**
 * 双路快速排序法
 */
@SuppressWarnings("all")
public class QuickSort2 {

    private static final Random RANDOM = new Random();

    private QuickSort2() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        process(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void process(E[] arr, int l, int r) {
        if (r - l <= 7) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        int p = partition(arr, l, r);

        process(arr, l, p - 1);
        process(arr, p + 1, r);
    }

    /**
     * 双路快速排序法
     */
    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
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

            if (p1 >= p2) break;

            swap(arr, p1, p2);
            p1++;
            p2--;
        }

        swap(arr, l, p2);
        return p2;
    }

    private static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

}
