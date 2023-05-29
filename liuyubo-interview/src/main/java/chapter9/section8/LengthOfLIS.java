package chapter9.section8;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/longest-increasing-subsequence/">300 - 最长递增子序列</a>
 */
public class LengthOfLIS {

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        // memo[i] = nums[0 ... i] 选择数字 nums[i] 可以获得的最长上升子序列的长度
        int[] memo = new int[nums.length];
        Arrays.fill(memo, 1);

        for (int i = 1; i < nums.length; i++) {
            // 求解 memo[i]
            for (int j = 0; j <= i - 1; j++) {
                if (nums[i] > nums[j]) memo[i] = Math.max(memo[i], 1 + memo[j]);
            }
        }

        return Arrays.stream(memo).max().getAsInt();
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); // 4
        System.out.println(lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6})); // 6
    }
}
