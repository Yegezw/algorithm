package chapter9.section5;

/**
 * 0 - 1 背包问题, 递归实现
 */
@SuppressWarnings("all")
public class Solution2 {

    /**
     * w[i] 代表重量, v[i] 代表价值, C 代表背包容量
     */
    public static int knapsack01(int[] w, int[] v, int C) {
        if (w == null || w.length == 0 || v == null || v.length == 0) return 0;
        if (w.length != v.length) return 0;

        return dp(w, v, w.length - 1, C);
    }

    /**
     * 用 w[0 ... index] 的物品, 填充最大容量为 c 的背包的最大价值
     */
    private static int dp(int[] w, int[] v, int index, int c) {
        if (index < 0 || c <= 0) return 0;

        // w[index] 的价值为 v[index], w[index] 要不要
        int res = dp(w, v, index - 1, c); // 不要
        if (c >= w[index]) {
            res = Math.max(res, v[index] + dp(w, v, index - 1, c - w[index])); // 要
        }

        return res;
    }

    public static void main(String[] args) {
        int[] w = {1, 2, 3};
        int[] v = {6, 10, 12};
        System.out.println(knapsack01(w, v, 5)); // 22
    }
}
