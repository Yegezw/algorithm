package chapter9.section4;

import java.util.Arrays;

public class Rob2 {

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return rob(nums, 0, memo);
    }

    /**
     * 考虑偷取 [x ... n - 1] 范围里的房子
     */
    private static int rob(int[] nums, int x, int[] memo) {
        if (x >= nums.length) return 0;

        if (memo[x] != -1) return memo[x];

        // 选择 nums[x] 要不要
        int res = Math.max(nums[x] + rob(nums, x + 2, memo), rob(nums, x + 1, memo));
        memo[x] = res;

        return memo[x];
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{3, 4, 1, 2})); // 6
        System.out.println(rob(new int[]{4, 3, 1, 2})); // 6
        System.out.println(rob(new int[]{1, 2, 3, 1})); // 4
        System.out.println(rob(new int[]{1, 3, 1, 3, 100})); // 103
    }
}
