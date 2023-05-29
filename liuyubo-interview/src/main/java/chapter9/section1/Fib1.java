package chapter9.section1;

public class Fib1 {

    private static int num;

    /**
     * <p>斐波那契数列 Fibonacci Sequence
     * <p>F(0) = 0, F(1) = 1, F(n) = F(n - 1) + F(n - 2)
     */
    public static int fib(int n) {
        num++;
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
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
