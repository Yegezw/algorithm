package chapter2.section3;

@SuppressWarnings("all")
public class Test {

    /**
     * O(logN)
     */
    private static int pow(int a, int n) {
        int res = 1;

        while (n > 0) {
            if ((n & 1) == 1) res *= a;
            n >>= 1;
            a *= a;
        }

        return res;
    }

    private static void testDataSize() {
        for (int x = 1; x <= 9; x++) {
            int n = pow(10, x);

            long startTime = System.nanoTime();
            int sum = 0;
            for (int i = 0; i < n; i++) sum += i;
            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println(String.format("10 ^ %d : %f s", x, time));
        }
    }

    /**
     * O(1)
     */
    private static void swap(char[] chars, int a, int b) {
        char k = chars[a];
        chars[a] = chars[b];
        chars[b] = k;
    }

    /**
     * O(N)
     */
    private static String revsrse(String s) {
        if (s.length() == 0) return "";

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            swap(chars, i, chars.length - 1 - i);
        }

        return new String(chars);
    }

    /**
     * O(logN)
     */
    private static String intToString(int num) {
        if (num == 0) return "0";

        boolean isNegative = false;
        if (num < 0) {
            isNegative = true;
            num = -num;
        }

        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append((char) ('0' + num % 10));
            num /= 10;
        }
        String str = revsrse(sb.toString());
        if (isNegative) str = "-" + str;

        return str;
    }

    /**
     * O(sqrt(N))
     * <p>数学上指在大于 1 的整数中, 只能被 1 和它本身整除的数
     */
    private static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int x = 2; x * x <= num; x++) {
            if (num % x == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        testDataSize();

        System.out.println(intToString(0));
        System.out.println(intToString(100));
        System.out.println(intToString(-100));

        System.out.println(isPrime(99991));
    }
}
