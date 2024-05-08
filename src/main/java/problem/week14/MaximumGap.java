package problem.week14;

import java.util.Arrays;

/**
 * <a href="https://leetcode-cn.com/problems/maximum-gap/">164 - 最大间距</a>
 */
public class MaximumGap
{

    public static int maximumGap(int[] nums)
    {
        if (nums.length < 2) return 0;

        int minV = Integer.MAX_VALUE;
        int maxV = -1;
        for (int num : nums)
        {
            minV = Math.min(minV, num);
            maxV = Math.max(maxV, num);
        }
        // 最大间距 >= minSpacing
        // 上取整, 最大间距的最小值 minSpacing = (maxV - minV) / (nums.length - 1)
        int minSpacing = (maxV - minV) / (nums.length - 1) + ((maxV - minV) % (nums.length - 1) != 0 ? 1 : 0);

        // 让桶内最大间距为 minSpacing, 这样可以保证最大间距的最大值不可能在桶内产生, 只能在桶间产生
        // 假设桶内元素的最小值为 x, 则桶内元素的取值范围为 [x ... x + minSpacing]
        int c     = minSpacing + 1;                       // c 代表每个桶中的元素可能数
        int range = maxV - minV + 1;                  // 根据元素可能数决定桶的个数
        int B     = range / c + (range % c != 0 ? 1 : 0); // B 个桶
        if (B == 1) return minSpacing;                // 如果只有 1 个桶, 直接返回 minSpacing
        int[] min = new int[B];
        int[] max = new int[B];
        Arrays.fill(min, Integer.MAX_VALUE);
        Arrays.fill(max, -1);
        for (int num : nums)
        {
            int p = (num - minV) / c;
            min[p] = Math.min(min[p], num);
            max[p] = Math.max(max[p], num);
        }

        int res     = 0;
        int lastMax = max[0];
        for (int i = 1; i < B; i++)
        {
            if (min[i] == Integer.MAX_VALUE) continue; // 有的桶可能就没用到
            res     = Math.max(res, min[i] - lastMax);
            lastMax = max[i];
        }

        return res;
    }

    public static void main(String[] args)
    {
        // int[] nums = {3, 6, 9, 1};
        int[] nums = {1, 1, 1, 1, 1, 1, 1, 2};
        System.out.println(maximumGap(nums));
    }
}
