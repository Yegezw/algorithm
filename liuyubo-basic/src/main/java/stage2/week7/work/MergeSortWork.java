package stage2.week7.work;

@SuppressWarnings("all")
public class MergeSortWork {

    private MergeSortWork() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        E[] temp = (E[]) new Comparable[arr.length];
        process(arr, 0, arr.length, temp);
    }

    /**
     * 归并排序 arr[l, r)
     */
    private static <E extends Comparable<E>> void process(E[] arr, int l, int r, E[] temp) {
        if (l >= r - 1) return;

        int mid = l + (r - l) / 2;
        process(arr, l, mid, temp); // arr[l, mid)
        process(arr, mid, r, temp); // arr[mid, r)

        merge(arr, l, mid, r, temp);
    }

    /**
     * 合并两个有序数组 arr[l, mid)、arr[mid, r), 使得 arr[l, r) 整体有序
     */
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp) {
        System.arraycopy(arr, l, temp, l, r - l);

        int p1 = l;
        int p2 = mid;
        int i = l;

        while (p1 < mid && p2 < r) arr[i++] = temp[p1].compareTo(temp[p2]) <= 0 ? temp[p1++] : temp[p2++];
        while (p1 < mid) arr[i++] = temp[p1++];
        while (p2 < r) arr[i++] = temp[p2++];
    }

}
