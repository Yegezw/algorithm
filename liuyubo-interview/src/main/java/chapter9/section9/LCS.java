package chapter9.section9;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/longest-common-subsequence/">1143 - 最长公共子序列</a>
 */
@SuppressWarnings("all")
public class LCS {

    private static int[][] memo;

    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) return 0;

        int m = text1.length();
        int n = text2.length();
        memo = new int[m][n];
        for (int[] arr : memo) Arrays.fill(arr, -1);

        return dp(text1.toCharArray(), text2.toCharArray(), m - 1, n - 1);
    }

    /**
     * s1[0 ... m] 和 s2[0 ... n] 的最长公共子序列
     */
    private static int dp(char[] s1, char[] s2, int m, int n) {
        if (m < 0 || n < 0) return 0;

        if (memo[m][n] != -1) return memo[m][n];

        int res = -1;
        if (s1[m] == s2[n]) res = 1 + dp(s1, s2, m - 1, n - 1);
        else res = Math.max(dp(s1, s2, m - 1, n), dp(s1, s2, m, n - 1));
        memo[m][n] = res;

        return res;
    }

    public static void main(String[] args) {
        String s1 = "AAACCGTGAGTTATTCGTTCTAGAA";
        String s2 = "CACCCCTAAGGTACCTTTGGTTC";
        System.out.println(longestCommonSubsequence(s1, s2)); // ACCTAGTATTGTTC 14
    }
}
