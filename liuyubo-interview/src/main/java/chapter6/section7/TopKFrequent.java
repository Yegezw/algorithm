package chapter6.section7;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/top-k-frequent-elements/">347 - 前 K 个高频元素</a>
 */
@SuppressWarnings("all")
public class TopKFrequent {

    private static class Pair implements Comparable<Pair> {
        public int value;
        public int freq;

        public Pair(int value, int freq) {
            this.value = value;
            this.freq = freq;
        }

        @Override
        public int compareTo(Pair o) {
            return this.freq - o.freq;
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {
        // 统计每个元素出现的频率 value : freq
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);

        // 扫描 map, 维护当前出现频率最高的 k 个元素
        PriorityQueue<Pair> pq = new PriorityQueue<>(); // 最小堆

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            Pair pair = new Pair(entry.getKey(), entry.getValue());

            if (pq.size() < k) pq.add(pair);
            else if (pair.freq > pq.peek().freq) {
                pq.remove();
                pq.add(pair);
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < res.length; i++) res[i] = pq.remove().value;
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 2, 3};
        System.out.println(Arrays.toString(topKFrequent(arr, 2)));
    }
}
