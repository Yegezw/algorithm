package stage2.week6.work;

/**
 * 快递排序法, 标定点为 mid
 */
@SuppressWarnings("all")
public class QuickSortMid {

    private QuickSortMid() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        process(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void process(E[] arr, int l, int r) {
        if (l >= r) return;

        int p = partition(arr, l, r);

        process(arr, l, p - 1);
        process(arr, p + 1, r);
    }

    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
        int mid = l + (r - l) / 2;
        swap(arr, l, mid);

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
