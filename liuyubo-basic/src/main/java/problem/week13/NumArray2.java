package problem.week13;

import java.util.Arrays;

/**
 * <a href="https://leetcode-cn.com/problems/range-sum-query-mutable/">307 - 区域和检索 - 数组可修改</a>
 * <p>通过 SQRT 分解解决
 */
@SuppressWarnings("all")
public class NumArray2 {

    private int[] data;
    private int[] blocks;
    private int N;  // 元素总数
    private int B;  // 每组元素个数
    private int Bn; // 组数

    public NumArray2(int[] nums) {
        N = nums.length;
        if (N == 0) return;

        B = (int) Math.sqrt(N);
        Bn = N / B + (N % B != 0 ? 1 : 0);

        data = Arrays.copyOf(nums, N);
        blocks = new int[Bn];
        for (int i = 0; i < N; i++) blocks[i / B] += data[i];
    }

    public void update(int index, int val) {
        if (index < 0 || index >= N) return;
        int diff = val - data[index];
        data[index] = val;
        blocks[index / B] += diff;
    }

    public int sumRange(int l, int r) {
        if (l < 0 || l >= N || r < 0 || r >= N || l > r) return 0;

        int sum = 0;
        int bStart = l / B;
        int bEnd = r / B;

        if (Math.abs(bStart - bEnd) <= 1) {
            for (int i = l; i <= r; i++) sum += data[i];
        } else {
            for (int i = l; i < (bStart + 1) * B; i++) sum += data[i]; // bStart 组
            for (int i = bStart + 1; i < bEnd; i++) sum += blocks[i];  // [bStart + 1 ... bEnd - 1] 组
            for (int i = bEnd * B; i <= r; i++) sum += data[i];        // bEnd 组
        }

        return sum;
    }
}
