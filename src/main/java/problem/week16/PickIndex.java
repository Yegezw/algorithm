package problem.week16;

import java.util.Random;

/**
 * <a href="https://leetcode.cn/problems/random-pick-with-weight/">528 - 按权重随机选择</a>
 */
public class PickIndex {

    private final int[] presum;
    private final int sum;
    private final Random random;

    public PickIndex(int[] w) {
        // w[i] 代表第 i 个任务的权重, 也就是说, 应该给第 i 个任务发放 w[i] 张彩票
        // 给每个任务发放彩票, 总共发了 sum(w[]) 张彩票
        // 我们只需要记录每个任务的彩票区间就好了, 更近一步, 只需要记录每个任务第一张彩票的编号
        // 因为下一个任务第一张彩票的编号 = 上一个任务最后一张彩票的编号 + 1
        // presum[i] = 第 i 个任务第一张彩票的编号
        presum = new int[w.length];
        for (int i = 1; i < presum.length; i++) presum[i] = presum[i - 1] + w[i - 1];
        sum = presum[w.length - 1] + w[w.length - 1];

        random = new Random();
    }

    public int pickIndex() {
        int x = random.nextInt(sum);

        // 二分查找 presum[i] <= x 的最大值对应的 i
        int l = 0;
        int r = presum.length - 1;
        int mid;
        while (l < r) {
            mid = l + (r - l + 1) / 2;
            if (presum[mid] <= x) l = mid;
            else r = mid - 1;
        }

        return l;
    }
}
