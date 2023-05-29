package chapter9.section7;

/**
 * <a href="https://leetcode.cn/problems/partition-equal-subset-sum/">416 - 分割等和子集</a>
 */
@SuppressWarnings("all")
public class CanPartition3 {

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        int C = sum / 2;

        // memo[c] = 用 nums[0 ... i] 中的数字, 能否完全填满一个容量为 c 的背包
        boolean[] memo = new boolean[C + 1];

        for (int c = 0; c <= C; c++) {
            // memo[c] = 用 nums[0] 中的数字, 能否完全填满一个容量为 c 的背包
            memo[c] = nums[0] == c;
        }

        for (int i = 1; i <= nums.length - 1; i++) {
            for (int c = C; c >= nums[i]; c--) {
                memo[c] = memo[c] || memo[c - nums[i]];
                if (i == nums.length - 1 && c == C) return memo[c]; // 在这里或许可以提前返回
            }
        }

        return memo[C];
    }

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 2, 5}));             // false
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));         // true
        System.out.println(canPartition(new int[]{1, 2, 3, 5}));          // false
        System.out.println(canPartition(new int[]{1, 2, 3, 4, 5, 6, 7})); // true
    }
}
