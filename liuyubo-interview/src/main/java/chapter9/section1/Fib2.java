package chapter9.section1;

import java.util.Arrays;

/**
 * <p>记忆化搜索
 * <p>自顶向下解决问题
 */
public class Fib2 {

    private static int num;

    /**
     * <p>斐波那契数列 Fibonacci Sequence
     * <p>F(0) = 0, F(1) = 1, F(n) = F(n - 1) + F(n - 2)
     */
    public static int fib(int n) {
        int[] memo = new int[n + 1]; // memo[i] = fib(i)
        Arrays.fill(memo, -1);
        return fib(n, memo);
    }

    private static int fib(int n, int[] memo) {
        num++;
        if (n <= 1) return n;

        if (memo[n] != -1) return memo[n];

        memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
        return memo[n];
    }

    private static void testTime(int n) {
        num = 0;

        double startTime = System.nanoTime();
        System.out.println("fib(" + n + ") = " + fib(n));
        double endTime = System.nanoTime();

        double time = (endTime - startTime) / 1e9;
        System.out.println("Time = " + time + " s");
        System.out.println("run function fib() " + num + " times");
    }

    public static void main(String[] args) {
        int n = 40;
        testTime(n);
    }
}
