package chapter9.section3;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/coin-change/">322 - 零钱兑换</a>
 */
public class CoinChange3 {

    public static int coinChange(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, amount + 1);

        memo[0] = 0;
        for (int i = 1; i <= amount; i++) {
            // 求解 memo[i]
            for (int coin : coins) {
                if (i < coin) continue;
                memo[i] = Math.min(memo[i], 1 + memo[i - coin]);
            }
        }

        return memo[amount] == amount + 1 ? -1 : memo[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{2}, 3)); // -1
        System.out.println(coinChange(new int[]{1, 2, 5}, 11)); // 3
    }
}
