package problem.week14;

import java.util.Arrays;

/**
 * <a href="https://leetcode-cn.com/problems/maximum-gap/">164 - 最大间距</a>
 */
public class MaximumGap {

    public static int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;
        if (nums.length == 2) return Math.abs(nums[0] - nums[1]);

        int minV = Integer.MAX_VALUE;
        int maxV = -1;
        for (int num : nums) {
            minV = Math.min(minV, num);
            maxV = Math.max(maxV, num);
        }
        if (maxV - minV <= 1) return maxV - minV;
        // 上取整, 最大间距的最小值 minSpacing = (maxV - minV) / (nums.length - 1)
        int minSpacing = (maxV - minV) / (nums.length - 1) + ((maxV - minV) % (nums.length - 1) != 0 ? 1 : 0);

        // c 代表每个桶中的元素可能数
        // c = minSpacing + 1, 因此桶内最大间距就是 minSpacing
        // 这样可以保证最大间距的最大值不可能在桶内产生, 只能在桶间产生
        int c = minSpacing + 1;
        int range = maxV - minV + 1; // nums 中的元素可能数
        int B = range / c + (range % c != 0 ? 1 : 0); // B 代表桶的个数
        int[] max = new int[B];
        int[] min = new int[B];
        Arrays.fill(max, -1);
        Arrays.fill(min, Integer.MAX_VALUE);
        for (int num : nums) {
            int p = (num - minV) / c;
            max[p] = Math.max(max[p], num);
            min[p] = Math.min(min[p], num);
        }

        int res = 0;
        int x = max[0];
        for (int i = 1; i < B; i++) {
            if (min[i] == Integer.MAX_VALUE) continue; // 有的桶可能就没用到
            res = Math.max(res, min[i] - x);
            x = max[i];
        }

        return res;
    }

    public static void main(String[] args) {
        // int[] nums = {3, 6, 9, 1};
        int[] nums = {1, 1, 1, 1, 1, 1, 1, 2};
        System.out.println(maximumGap(nums));
    }
}
