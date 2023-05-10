package chapter2.section5;

public class Recursion {

    /**
     * O(2 ^ N)
     */
    private static int f(int n) {
        if (n == 0) return 1;
        return f(n - 1) + f(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(f(5));
    }
}
