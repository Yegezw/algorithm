package stage2.week7.work;

@SuppressWarnings("all")
public class MergeSortWork
{

    private MergeSortWork()
    {
    }

    public static <E extends Comparable<E>> void sort(E[] arr)
    {
        E[] temp = (E[]) new Comparable[arr.length];
        sort(arr, 0, arr.length, temp);
    }

    /**
     * 归并排序 arr[l, r)
     */
    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, E[] temp)
    {
        if (l >= r - 1) return;

        int mid = l + (r - l) / 2;
        sort(arr, l, mid, temp); // arr[l, mid)
        sort(arr, mid, r, temp); // arr[mid, r)

        if (arr[mid - 1].compareTo(arr[mid]) > 0) merge(arr, l, mid, r, temp);
    }

    /**
     * 合并两个有序数组 arr[l, mid) 和 arr[mid, r), 使得 arr[l, r) 整体有序
     */
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp)
    {
        System.arraycopy(arr, l, temp, l, r - l);

        int p1 = l;   // temp[l, mid)
        int p2 = mid; // temp[mid, r)
        int i  = l;    // arr[l, r)

        while (p1 < mid && p2 < r) arr[i++] = temp[p1].compareTo(temp[p2]) <= 0 ? temp[p1++] : temp[p2++];
        while (p1 < mid) arr[i++] = temp[p1++];
        while (p2 < r) arr[i++] = temp[p2++];
    }
}
