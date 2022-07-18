package practice.sort;

import java.util.Random;

/**
 * 练习三种快速排序的 Partition 过程
 */
@SuppressWarnings("all")
public class PartitionP {

    private static final Random RANDOM = new Random();

    private PartitionP() {
    }

    /**
     * 单路快速排序
     */
    public static <E extends Comparable<E>> int partition1(E[] arr, int l, int r) {
        int p = RANDOM.nextInt(r - l + 1) + l;
        swap(arr, l, p);

        E v = arr[l];
        int j = l;

        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(v) < 0) swap(arr, i, ++j);
        }

        swap(arr, l, j);
        return j;
    }

    /**
     * 双路快速排序
     */
    public static <E extends Comparable<E>> int partition2(E[] arr, int l, int r) {
        int p = RANDOM.nextInt(r - l + 1) + l;
        swap(arr, l, p);

        E v = arr[l];
        int p1 = l + 1;
        int p2 = r;

        while (true) {
            while (p1 <= p2 && arr[p1].compareTo(v) < 0) p1++;
            while (p1 <= p2 && arr[p2].compareTo(v) > 0) p2--;

            if (p1 >= p2) break;

            swap(arr, p1++, p2--);
        }

        swap(arr, l, p2);
        return p2;
    }

    /**
     * 三路快速排序
     */
    public static <E extends Comparable<E>> int[] partition3(E[] arr, int l, int r) {
        int p = RANDOM.nextInt(r - l + 1) + l;
        swap(arr, l, p);

        E v = arr[l];
        int p1 = l;
        int p2 = r + 1;

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
        E k =arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    public static <E extends Comparable<E>> void insertionSort(E[] arr, int l, int r) {
        for (int i = r - 1; i >= l; i--) {
            E k = arr[i];
            int j;
            for (j = i; j + 1 <= r && k.compareTo(arr[j + 1]) > 0; j++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = k;
        }
    }

}
