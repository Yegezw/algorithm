package problem.week9;

import java.util.Arrays;

/**
 * <a href="https://leetcode-cn.com/problems/kth-largest-element-in-an-array/">215 - 数组中的第 K 个最大元素</a>
 * <p>求数组升序排序好后, 从右往左数第 K 个元素, 它的索引是 length - K
 * <p>直接使用最小堆
 * <p>复杂度: O(N * logK)
 */
@SuppressWarnings("all")
public class FindKthLargest_1
{

    public static int findKthLargest(int[] arr, int k)
    {
        int[] minHeap = Arrays.copyOf(arr, k);
        int   last    = k - 1;
        for (int i = (last - 1) / 2; i >= 0; i--) siftDown(minHeap, i);

        for (int i = k; i < arr.length; i++)
        {
            if (arr[i] > minHeap[0])
            {
                minHeap[0] = arr[i];
                siftDown(minHeap, 0);
            }
        }

        return minHeap[0];
    }

    private static void siftDown(int[] arr, int index)
    {
        int last = arr.length - 1;
        while (index * 2 + 1 <= last)
        {
            int smaller = index * 2 + 1;
            if (smaller + 1 <= last && arr[smaller + 1] < arr[smaller]) smaller++;

            if (arr[index] <= arr[smaller]) break;
            swap(arr, index, smaller);
            index = smaller;
        }
    }

    private static void swap(int[] arr, int a, int b)
    {
        int k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    public static void main(String[] args)
    {
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6}; // k = 4, res = 4
        System.out.println(findKthLargest(arr, 4));
    }
}
