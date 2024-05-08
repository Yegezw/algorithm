package problem.week15;

/**
 * <a href="https://leetcode-cn.com/problems/longest-chunked-palindrome-decomposition/">1147 - 段式回文</a>
 */
public class LongestDecomposition
{

    private final long   MOD = (long) (1e9 + 7);
    private       long[] pow26;

    public int longestDecomposition(String text)
    {
        // pow26[i] = (26 ^ i) % MOD
        pow26    = new long[text.length()];
        pow26[0] = 1;
        for (int i = 1; i < pow26.length; i++) pow26[i] = (pow26[i - 1] * 26) % MOD;

        return solve(text, 0, text.length() - 1);
    }

    /**
     * s[left ... right]
     */
    private int solve(String s, int left, int right)
    {
        if (left > right) return 0;

        int  B        = 26;
        long prevHash = 0;
        long postHash = 0;
        for (int l = left, r = right; l < r; l++, r--)
        {
            // s[left ... l] == s[r ... right] ? 有可能存在哈希冲突
            prevHash = (prevHash * B + (s.charAt(l) - 'a')) % MOD;
            postHash = ((s.charAt(r) - 'a') * pow26[right - r] + postHash) % MOD;

            if (prevHash == postHash && equal(s, left, l, r, right)) return 2 + solve(s, l + 1, r - 1);
        }

        return 1;
    }

    /**
     * s[l1 ... r1] == s[l2 ... r2] ?
     */
    private boolean equal(String s, int l1, int r1, int l2, int r2)
    {
        for (; l1 <= r1 && l2 <= r2; l1++, l2++)
        {
            if (s.charAt(l1) != s.charAt(l2)) return false;
        }
        return true;
    }
}
