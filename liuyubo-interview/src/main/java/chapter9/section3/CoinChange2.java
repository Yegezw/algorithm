package chapter9.section3;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/coin-change/">322 - 零钱兑换</a>
 */
public class CoinChange2 {

    public static int coinChange(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -10);
        return dp(coins, amount, memo);
    }

    private static int dp(int[] coins, int amount, int[] memo) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;

        if (memo[amount] != -10) return memo[amount];

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblem = dp(coins, amount - coin, memo);
            if (subProblem == -1) continue;
            res = Math.min(res, 1 + subProblem);
        }
        memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;

        return memo[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{2}, 3)); // -1
        System.out.println(coinChange(new int[]{1, 2, 5}, 11)); // 3
    }
}
