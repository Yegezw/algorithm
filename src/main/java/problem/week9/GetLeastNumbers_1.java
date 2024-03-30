package problem.week9;

import java.util.Arrays;

/**
 * <a href="https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/">剑指 Offer 40 - 最小的 K 个数</a>
 * <p>求数组升序排序好后, 从左往右数共 K 个元素
 * <p>直接使用最大堆
 * <p>复杂度: O(N * logK)
 */
@SuppressWarnings("all")
public class GetLeastNumbers_1 {

    public static int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) return new int[0];

        int[] maxHeap = Arrays.copyOf(arr, k);
        int last = k - 1;
        for (int i = (last - 1) / 2; i >= 0; i--) siftDown(maxHeap, i);

        for (int i = k; i < arr.length; i++) {
            if (arr[i] < maxHeap[0]) {
                maxHeap[0] = arr[i];
                siftDown(maxHeap, 0);
            }
        }

        return maxHeap;
    }

    private static void siftDown(int[] arr, int index) {
        int last = arr.length - 1;
        while (index * 2 + 1 <= last) {
            int bigger = index * 2 + 1;
            if (bigger + 1 <= last && arr[bigger + 1] > arr[bigger]) bigger++;

            if (arr[index] >= arr[bigger]) break;
            swap(arr, index, bigger);
            index = bigger;
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    public static void main(String[] args) {
        int[] arr = {3, 9, 7, 5, 4, 1, 6, 8, 2};
        System.out.println(Arrays.toString(getLeastNumbers(arr, 4))); // [4, 3, 2, 1]
    }
}
