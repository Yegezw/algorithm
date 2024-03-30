package stage3.week9.work;

/**
 * <p>堆排序: O(N * logN)
 * <p>基于最小堆来实现
 */
@SuppressWarnings("all")
public class HeapSort {

    private HeapSort() {
    }

    public static <E extends Comparable<E>> void sort1(E[] arr) {
        MinHeap<E> minHeap = new MinHeap<>(arr.length);
        for (E e : arr) minHeap.add(e);
        for (int i = arr.length - 1; i >= 0; i--) arr[i] = minHeap.extractMin();
    }

    public static <E extends Comparable<E>> void sort2(E[] arr) {
        if (arr == null || arr.length <= 1) return;

        int last = arr.length - 1;
        for (int i = (last - 1) / 2; i >= 0; i--) siftDown(arr, i, last);

        while (last >= 1) {
            swap(arr, 0, last);
            last--;
            siftDown(arr, 0, last);
        }
    }

    /**
     * 对 arr[0, last] 所形成的最小堆中, 索引 index 的元素, 执行 siftDown
     */
    private static <E extends Comparable<E>> void siftDown(E[] arr, int index, int last) {
        while (index * 2 + 1 <= last) {
            int smaller = index * 2 + 1;
            if (smaller + 1 <= last && arr[smaller + 1].compareTo(arr[smaller]) < 0) smaller++;

            if (arr[index].compareTo(arr[smaller]) <= 0) break;
            swap(arr, index, smaller);
            index = smaller;
        }
    }

    private static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }
}
