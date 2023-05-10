package chapter3.section7;

/**
 * <a href="https://leetcode.cn/problems/minimum-size-subarray-sum/">209 - 长度最小的子数组</a>
 */
public class MinSubArrayLen {

    /**
     * <p>时间复杂度: O(N)
     * <p>空间复杂度: O(1)
     * <p>滑动窗口算法通常使用双指针来维护窗口的左右边界, 其中右指针用于扩展窗口, 左指针用于缩小窗口
     * <p>在每一次移动窗口时, 我们都可以根据窗口的状态来更新答案
     */
    public static int minSubArrayLen1(int target, int[] nums) {
        // nums[l ... r] 是滑动窗口
        int l = 0;
        int r = -1;
        int sum = 0;
        int res = nums.length + 1;

        // 窗口的左边界在数组范围内, 则循环继续
        while (l < nums.length) {
            if (r + 1 < nums.length && sum < target) sum += nums[++r];
            else sum -= nums[l++];

            if (sum >= target) res = Math.min(res, r - l + 1);
        }

        return res != nums.length + 1 ? res : 0;
    }

    public static int minSubArrayLen2(int target, int[] nums) {
        // nums[l ... r] 是滑动窗口
        int l = 0;
        int r = -1;
        int sum = 0;
        int res = nums.length + 1;

        // 窗口的右边界可以继续扩展, 则循环继续
        while (r + 1 < nums.length) {
            // r 用于扩展窗口
            while (r + 1 < nums.length && sum < target) sum += nums[++r];
            if (sum >= target) res = Math.min(res, r - l + 1);

            // l 用于缩小窗口
            while (l <= r && sum >= target) {
                sum -= nums[l++];
                if (sum >= target) res = Math.min(res, r - l + 1);
            }
        }

        return res != nums.length + 1 ? res : 0;
    }


    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen1(7, nums));
        System.out.println(minSubArrayLen2(7, nums));
    }
}
