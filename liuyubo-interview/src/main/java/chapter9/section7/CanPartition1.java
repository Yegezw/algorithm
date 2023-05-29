package chapter9.section7;

/**
 * <a href="https://leetcode.cn/problems/partition-equal-subset-sum/">416 - 分割等和子集</a>
 */
@SuppressWarnings("all")
public class CanPartition1 {

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        int c = sum / 2;

        return dp(nums, nums.length - 1, c);
    }

    /**
     * 使用 nums[0 ... index] 中的数字, 能否完全填满一个容量为 c 的背包
     */
    private static boolean dp(int[] nums, int index, int c) {
        if(c == 0) return true;
        if (index < 0 || c < 0) return false;

        // nums[index] 要不要
        boolean res = false;
        res = res || dp(nums, index - 1, c); // 不要
        res = res || dp(nums, index - 1, c - nums[index]); // 要

        return res;
    }

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 2, 5}));             // false
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));         // true
        System.out.println(canPartition(new int[]{1, 2, 3, 5}));          // false
        System.out.println(canPartition(new int[]{1, 2, 3, 4, 5, 6, 7})); // true
    }
}
