package stage2.week6.quicksort;

import stage1.week2.InsertionSort;

import java.util.Random;

/**
 * <p>三路快速排序: O(N * logN)
 * <p>所有元素都相同的数组 O(n)
 */
@SuppressWarnings("all")
public class QuickSort3
{

    private static final Random RANDOM = new Random();

    private QuickSort3()
    {
    }

    public static <E extends Comparable<E>> void sort(E[] arr)
    {
        sort(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r)
    {
        if (r - l <= 15)
        {
            InsertionSort.sort(arr, l, r);
            return;
        }

        int[] p = partition(arr, l, r);

        sort(arr, l, p[0]);
        sort(arr, p[1], r);
    }

    private static <E extends Comparable<E>> int[] partition(E[] arr, int l, int r)
    {
        int p = RANDOM.nextInt(r - l + 1) + l;
        swap(arr, l, p);

        E   v  = arr[l];
        int p1 = l;
        int p2 = r + 1;

        // arr[l + 1, p1] < v, arr[p2, r] > v
        // for (int i = l + 1; i < p2; i++) {
        //    if (arr[i].compareTo(v) < 0) swap(arr, i, ++p1);
        //    else if (arr[i].compareTo(v) > 0) {
        //        swap(arr, i, --p2);
        //        i--;
        //    }
        // }

        // arr[l + 1, p1] < v, arr[p2, r] > v
        int i = l + 1;
        while (i < p2)
        {
            if (arr[i].compareTo(v) < 0) swap(arr, ++p1, i++);
            else if (arr[i].compareTo(v) > 0) swap(arr, --p2, i);
            else i++;
        }

        swap(arr, l, p1);
        return new int[]{p1 - 1, p2};
    }

    private static <E> void swap(E[] arr, int a, int b)
    {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }
}
