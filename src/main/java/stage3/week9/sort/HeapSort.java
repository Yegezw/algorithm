package stage3.week9.sort;

import stage3.week9.heap.MaxHeap;

/**
 * <p>堆排序: O(N * logN)
 * <p>基于最大堆来实现
 */
@SuppressWarnings("all")
public class HeapSort
{

    private HeapSort()
    {
    }

    /**
     * 自顶向下建堆: O(N * logN)
     */
    public static <E extends Comparable<E>> void sort1(E[] arr)
    {
        MaxHeap<E> maxHeap = new MaxHeap<>(arr.length);
        for (E e : arr) maxHeap.add(e); // 自顶向下建堆: O(N * logN)
        for (int i = arr.length - 1; i >= 0; i--) arr[i] = maxHeap.extractMax();
    }

    /**
     * 堆排序优化, 自底向上建堆: O(n)
     */
    public static <E extends Comparable<E>> void sort2(E[] arr)
    {
        if (arr == null || arr.length <= 1) return;

        // 把 arr[] 变成最大堆, 自底向上建堆: O(n)
        int last = arr.length - 1;
        for (int i = (last - 1) / 2; i >= 0; i--) siftDown(arr, i, last);

        while (last >= 1)
        {
            swap(arr, 0, last);
            last--;
            siftDown(arr, 0, last);
        }
    }

    /**
     * 对 arr[0, last] 所形成的最大堆中, 索引 index 的元素, 执行 siftDown
     */
    private static <E extends Comparable<E>> void siftDown(E[] arr, int index, int last)
    {
        while (index * 2 + 1 <= last)
        {
            int bigger = index * 2 + 1;
            if (bigger + 1 <= last && arr[bigger + 1].compareTo(arr[bigger]) > 0) bigger++;

            if (arr[index].compareTo(arr[bigger]) >= 0) break;
            swap(arr, index, bigger);
            index = bigger;
        }
    }

    private static <E> void swap(E[] arr, int a, int b)
    {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }
}
