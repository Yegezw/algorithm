package stage2.week6.quicksort;

import stage1.week2.InsertionSort;

import java.util.Random;

/**
 * <p>单路快速排序: O(N * logN)
 * <p>当数组中的元素一致时退化为 O(n^2)
 */
@SuppressWarnings("all")
public class QuickSort1
{

    private static final Random RANDOM = new Random();

    private QuickSort1()
    {
    }

    /**
     * 快速排序
     */
    public static <E extends Comparable<E>> void sort(E[] arr)
    {
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序 arr[l, r]
     */
    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r)
    {
        if (r - l <= 15)
        {
            InsertionSort.sort(arr, l, r);
            return;
        }

        int p = partition(arr, l, r);

        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    /**
     * 单路快排
     */
    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r)
    {
        int p = RANDOM.nextInt(r - l + 1) + l;
        swap(arr, l, p);

        E   v = arr[l];
        int j = l;

        // arr[l + 1, j] < v, arr[j + 1, r] >= v
        for (int i = l + 1; i <= r; i++)
        {
            if (arr[i].compareTo(v) < 0) swap(arr, i, ++j);
        }

        swap(arr, l, j);
        return j;
    }

    private static <E> void swap(E[] arr, int a, int b)
    {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }
}
