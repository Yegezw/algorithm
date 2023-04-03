package problem.week9;

import stage3.week9.work.PriorityQueue;

/**
 * <a href="https://leetcode-cn.com/problems/kth-largest-element-in-an-array/">215 - 数组中的第 K 个最大元素</a>
 * <p>求数组升序排序好后, 从右往左数第 K 个元素, 它的索引是 length - K
 * <p>使用自己封装的 PriorityQueue
 * <p>复杂度: O(N * logK)
 */
@SuppressWarnings("all")
public class FindKthLargest_2 {

    public static int findKthLargest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) pq.enqueue(arr[i]);
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > pq.getFront()) {
                pq.dequeue();
                pq.enqueue(arr[i]);
            }
        }
        
        return pq.getFront();
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6}; // k = 4, res = 4
        System.out.println(findKthLargest(arr, 4));
    }
}
