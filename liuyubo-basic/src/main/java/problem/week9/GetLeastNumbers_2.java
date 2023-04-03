package problem.week9;

import stage3.week9.queue.PriorityQueue;

import java.util.Arrays;

/**
 * <a href="https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/">剑指 Offer 40 - 最小的 K 个数</a>
 * <p>求数组升序排序好后, 从左往右数共 K 个元素
 * <p>使用自己封装的 PriorityQueue
 * <p>复杂度: O(N * logK)
 */
@SuppressWarnings("all")
public class GetLeastNumbers_2 {

    public static int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) return new int[0];

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) pq.enqueue(arr[i]);

        for (int i = k; i < arr.length; i++) {
            if (arr[i] < pq.getFront()) {
                pq.dequeue();
                pq.enqueue(arr[i]);
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) res[i] = pq.dequeue();
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {3, 9, 7, 5, 4, 1, 6, 8, 2};
        System.out.println(Arrays.toString(getLeastNumbers(arr, 4))); // [4, 3, 2, 1]
    }
}
