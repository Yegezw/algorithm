package chapter9.section4;

public class Rob1 {

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int[] memo = new int[nums.length];
        memo[0] = nums[0];
        memo[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < memo.length; i++) {
            // 求解 memo[i], 选择 nums[i] 要不要
            memo[i] = Math.max(memo[i - 2] + nums[i], memo[i - 1]);
        }

        return memo[memo.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{3, 4, 1, 2})); // 6
        System.out.println(rob(new int[]{4, 3, 1, 2})); // 6
        System.out.println(rob(new int[]{1, 2, 3, 1})); // 4
        System.out.println(rob(new int[]{1, 3, 1, 3, 100})); // 103
    }
}
