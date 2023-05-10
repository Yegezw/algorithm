package chapter4.section5;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/4sum-ii/">454 - 四数相加 II</a>
 */
public class FourSumCount {

    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // Key -> i + j, Value -> 个数
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

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{-2, -1};
        int[] nums3 = new int[]{-1, 2};
        int[] nums4 = new int[]{0, 2};
        System.out.println(fourSumCount(nums1, nums2, nums3, nums4));
    }
}
