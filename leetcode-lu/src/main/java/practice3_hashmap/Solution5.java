package practice3_hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/4sum-ii/">454 - 四数相加 II</a>
 */
public class Solution5 {

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // sum : 个数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            for (int j : nums2) {
                int sum = i + j;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int res = 0;
        for (int k : nums3) {
            for (int l : nums4) {
                int sum = k + l;
                res += map.getOrDefault(-sum, 0);
            }
        }

        return res;
    }
}
