package stage2.week6.quicksort;

import java.util.Random;

@SuppressWarnings("all")
public class Partition
{

    private static final Random RANDOM = new Random();

    private Partition()
    {
    }

    /**
     * 单路快排
     */
    public static <E extends Comparable<E>> int partition1(E[] arr, int l, int r)
    {
        int p = RANDOM.nextInt(r - l + 1) + l;
        swap(arr, l, p);

        E   v = arr[l];
        int j = l;

        for (int i = l + 1; i <= r; i++)
        {
            if (arr[i].compareTo(v) < 0) swap(arr, i, ++j);
        }

        swap(arr, l, j);
        return j;
    }

    /**
     * 双路快排
     */
    public static <E extends Comparable<E>> int partition2(E[] arr, int l, int r)
    {
        int p = RANDOM.nextInt(r - l + 1) + l;
        swap(arr, l, p);

        E   v  = arr[l];
        int p1 = l + 1;
        int p2 = r;

        while (true)
        {
            while (p1 <= p2 && arr[p1].compareTo(v) < 0) p1++;
            while (p1 <= p2 && arr[p2].compareTo(v) > 0) p2--;

            if (p1 >= p2) break;

            swap(arr, p1++, p2--);
        }

        swap(arr, l, p2);
        return p2;
    }

    /**
     * 三路快排
     */
    public static <E extends Comparable<E>> int[] partition3(E[] arr, int l, int r)
    {
        int p = RANDOM.nextInt(r - l + 1) + l;
        swap(arr, l, p);

        E   v  = arr[l];
        int p1 = l;
        int p2 = r + 1;

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

    private static <E extends Comparable<E>> void insertionSort(E[] arr, int l, int r)
    {
        for (int i = l + 1; i <= r; i++)
        {
            E   k = arr[i];
            int j;
            for (j = i; j - 1 >= l && arr[j - 1].compareTo(k) > 0; j--)
            {
                arr[j] = arr[j - 1];
            }
            arr[j] = k;
        }
    }

    private static <E> void swap(E[] arr, int a, int b)
    {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }
}
