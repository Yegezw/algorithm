package stage4.week15;

/**
 * Rabin-Karp 算法, 滚动哈希思想 O(n)
 */
public class RabinKarp
{

    private RabinKarp()
    {
    }

    public static int rabinKarp(String s, String t)
    {
        if (t.isEmpty()) return 0;
        if (s.length() < t.length()) return -1;

        int  B   = 256;
        long MOD = (long) (1e9 + 7);
        long P   = 1; // P = B ^ (t.length - 1) % MOD
        for (int i = 0; i < t.length() - 1; i++) P = P * B % MOD;

        // targetHash = hash(t)
        long targetHash = 0;
        for (int i = 0; i < t.length(); i++) targetHash = (targetHash * B + t.charAt(i)) % MOD;

        // curHash = hash(s[0 ... t.length - 2])
        long curHash = 0;
        for (int i = 0; i < t.length() - 1; i++) curHash = (curHash * B + s.charAt(i)) % MOD;

        // hash(s[i - t.length + 1 ... i])
        for (int i = t.length() - 1; i < s.length(); i++)
        {
            curHash = (curHash * B + s.charAt(i)) % MOD;
            if (curHash == targetHash && equal(s, i - t.length() + 1, i, t)) return i - t.length() + 1;
            curHash = (curHash - s.charAt(i - t.length() + 1) * P % MOD + MOD) % MOD; // 注意
        }

        return -1;
    }

    /**
     * s[l ... r] == t ?
     */
    private static boolean equal(String s, int l, int r, String t)
    {
        for (int i = 0; i < t.length(); i++)
        {
            if (s.charAt(l + i) != t.charAt(i)) return false;
        }
        return true;
    }
}
