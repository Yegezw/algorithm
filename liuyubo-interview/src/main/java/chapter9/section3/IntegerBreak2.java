package chapter9.section3;

public class IntegerBreak2 {

    public static int integerBreak(int n) {
        // memo[i] 表示将数字 i 进行分割(至少分割两部分)后得到的最大乘积
        int[] memo = new int[n + 1];
        memo[2] = 1; // 2 -> 1 + 1

        // memo[3] ... memo[n]
        for (int i = 3; i <= n; i++) {
            // 求解 memo[i]
            int res = -1;
            for (int j = 1; j <= i - 1; j++) {
                // i = j + (i - j)
                res = max3(res, j * (i - j), j * memo[i - j]);
            }
            memo[i] = res;
        }

        return memo[n];
    }

    private static int max3(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    public static void main(String[] args) {
        System.out.println(integerBreak(10)); // 36
    }
}
