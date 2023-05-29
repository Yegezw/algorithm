package chapter9.section3;

public class IntegerBreak1 {

    private static int[] memo;

    public static int integerBreak(int n) {
        memo = new int[n + 1]; // memo[i] = integerBreak(i)
        return breakInteger(n);
    }

    /**
     * 将 n 进行分割(至少分割两部分), 可以获得的最大乘积
     */
    private static int breakInteger(int n) {
        if (n == 2) return 1;

        if (memo[n] != 0) return memo[n];

        int res = -1;
        for (int i = 1; i <= n - 1; i++) {
            // i + (n - i)
            res = max3(res, i * (n - i), i * breakInteger(n - i));
        }
        memo[n] = res;

        return res;
    }

    private static int max3(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    public static void main(String[] args) {
        System.out.println(integerBreak(10)); // 36
    }
}
