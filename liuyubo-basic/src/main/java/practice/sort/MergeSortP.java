package practice.sort;

/**
 * 练习归并排序
 */
@SuppressWarnings("all")
public class MergeSortP {

    private MergeSortP() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        E[] temp = (E[]) new Comparable[arr.length];
        process(arr, 0, arr.length - 1, temp);
    }

    private static <E extends Comparable<E>> void process(E[] arr, int l, int r, E[] temp) {
        if (r - l <= 15) {
            insertionSort(arr, l, r);
            return;
        }

        int mid = l + (r - l) / 2;
        process(arr, l, mid, temp);
        process(arr, mid + 1, r, temp);

        if (arr[mid].compareTo(arr[mid + 1]) > 0) merge(arr, l, mid, r, temp);
    }

    private static <E extends Comparable<E>> void insertionSort(E[] arr, int l, int r) {
        for (int i = r - 1; i >= l; i--) {
            E k = arr[i];
            int j;
            for (j = i; j + 1 <= r && k.compareTo(arr[j + 1]) > 0; j++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = k;
        }
    }

    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp) {
        System.arraycopy(arr, l, temp, l, r- l + 1);

        int p1 = l;
        int p2 = mid + 1;
        int i = l;

        while (p1 <= mid && p2 <= r) arr[i++] = temp[p1].compareTo(temp[p2]) < 0 ? temp[p1++] : temp[p2++];
        while (p1 <= mid) arr[i++] = temp[p1++];
        while (p2 <= r) arr[i++] = temp[p2++];
    }

}
