package chapter9.section6;

import java.util.Arrays;

/**
 * 0 - 1 背包问题, 动态规划, 空间优化 1
 */
@SuppressWarnings("all")
public class Solution1 {

    /**
     * w[i] 代表重量, v[i] 代表价值, C 代表背包容量
     */
    public static int knapsack01(int[] w, int[] v, int C) {
        if (w == null || w.length == 0 || v == null || v.length == 0) return 0;
        if (w.length != v.length) return 0;

        // 用 w[0 ... i] 的物品, 填充最大容量为 c 的背包的最大价值 = memo[i % 2][c]
        int[][] memo = new int[2][C + 1];
        for (int[] arr : memo) Arrays.fill(arr, -1);

        for (int c = 0; c <= C; c++) {
            // 用 w[0] 的物品, 填充最大容量为 c 的背包的最大价值 = memo[0][c]
            memo[0][c] = (c >= w[0] ? v[0] : 0);
        }

        for (int i = 1; i <= w.length - 1; i++) {
            for (int c = 0; c <= C; c++) {
                // 用 w[0 ... i] 的物品, 填充最大容量为 c 的背包的最大价值 = memo[i % 2][c]
                // w[i] 的价值为 v[i], w[i] 要不要
                int res = memo[(i - 1) % 2][c]; // 不要
                if (c >= w[i]) {
                    res = Math.max(res, v[i] + memo[(i - 1) % 2][c - w[i]]); // 要
                }
                memo[i % 2][c] = res;
            }
        }

        return memo[(w.length - 1) % 2][C];
    }

    public static void main(String[] args) {
        int[] w = {1, 2, 3};
        int[] v = {6, 10, 12};
        System.out.println(knapsack01(w, v, 5)); // 22
    }
}
