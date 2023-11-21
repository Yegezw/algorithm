package practice5_stack_queue;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.cn/problems/top-k-frequent-elements/description/">347 - 前 K 个高频元素</a>
 */
public class Solution7 {

    private class Node {
        public int num;
        public int count;

        public Node(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        // 值 : 次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 根据次数来比较的最大堆
        PriorityQueue<Node> maxHeap = new PriorityQueue<>((o1, o2) -> o2.count - o1.count);
        map.forEach((num, count) -> maxHeap.add(new Node(num, count)));

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = maxHeap.remove().num;
        }
        return res;
    }
}
