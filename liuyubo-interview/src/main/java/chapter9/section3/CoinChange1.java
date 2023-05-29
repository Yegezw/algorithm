package chapter9.section3;

/**
 * <a href="https://leetcode.cn/problems/coin-change/">322 - 零钱兑换</a>
 */
public class CoinChange1 {

    public static int coinChange(int[] coins, int amount) {
        return dp(coins, amount);
    }

    private static int dp(int[] coins, int amount) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblem = dp(coins, amount - coin);
            if (subProblem == -1) continue;
            res = Math.min(res, 1 + subProblem);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{2}, 3)); // -1
        System.out.println(coinChange(new int[]{1, 2, 5}, 11)); // 3
    }
}
