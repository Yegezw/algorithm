package other.util;

/**
 * 快速幂运算
 */
public class Power {

    private static final long MOD = (long) (1e9 + 7);

    private Power() {
    }

    /**
     * 递归快速幂
     */
    public static long powR(long a, int n) {
        if (n == 0) return 1;

        if (n % 2 == 1) return powR(a, n - 1) * a % MOD;
        else {
            long temp = powR(a, n / 2) % MOD;
            return temp * temp % MOD;
        }
    }

    /**
     * 非递归快速幂
     */
    public static long pow(long a, int n) {
        long res = 1;

        while (n > 0) {
            if ((n & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            n >>= 1;
        }

        return res;
    }
}
