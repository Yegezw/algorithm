package stage2.week6.quicksort;

import stage1.week2.InsertionSort;

import java.util.Random;

/**
 * 三路快速排序法
 */
@SuppressWarnings("all")
public class QuickSort3 {

    private static final Random RANDOM = new Random();

    private QuickSort3() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        process(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void process(E[] arr, int l, int r) {
        if (r - l <= 7) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        int[] p = partition(arr, l, r);

        process(arr, l, p[0]);
        process(arr, p[1], r);
    }

    /**
     * 三路快速排序法
     */
    private static <E extends Comparable<E>> int[] partition(E[] arr, int l, int r) {
        int p = RANDOM.nextInt(r - l + 1) + l;
        swap(arr, l, p);

        E v = arr[l];
        int p1 = l;
        int p2 = r + 1;

        // arr[l + 1, p1] < v, arr[p2, r] > v
        //for (int i = l + 1; i < p2; i++) {
        //    if (arr[i].compareTo(v) < 0) swap(arr, i, ++p1);
        //    else if (arr[i].compareTo(v) > 0) {
        //        swap(arr, i, --p2);
        //        i--;
        //    }
        //}

        // arr[l + 1, p1] < v, arr[p2, r] > v
        int i = l + 1;
        while (i < p2) {
            if (arr[i].compareTo(v) < 0) swap(arr, i++, ++p1);
            else if (arr[i].compareTo(v) > 0) swap(arr, i, --p2);
            else i++;
        }

        swap(arr, l, p1);
        return new int[]{p1 - 1, p2};
    }

    private static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

}
