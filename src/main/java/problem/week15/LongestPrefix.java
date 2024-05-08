package problem.week15;

/**
 * <a href="https://leetcode-cn.com/problems/longest-happy-prefix/">1392 - 最长快乐前缀</a>
 */
public class LongestPrefix
{

    private final long   MOD = (long) (1e9 + 7);
    private       long[] pow26;

    public String longestPrefix(String s)
    {
        // pow26[i] = (26 ^ i) % MOD
        pow26    = new long[s.length()];
        pow26[0] = 1;
        for (int i = 1; i < pow26.length; i++) pow26[i] = (pow26[i - 1] * 26) % MOD;

        // prevHash[i] = hash(s[0 ... i])
        long[] prevHash = new long[s.length()];
        prevHash[0] = s.charAt(0) - 'a';
        for (int i = 1; i < s.length(); i++)
        {
            prevHash[i] = (prevHash[i - 1] * 26 + s.charAt(i) - 'a') % MOD;
        }

        // postHash[i] = hash(s[i ... s.length - 1])
        long[] postHash = new long[s.length()];
        postHash[postHash.length - 1] = s.charAt(s.length() - 1) - 'a';
        for (int i = postHash.length - 2; i >= 0; i--)
        {
            postHash[i] = ((s.charAt(i) - 'a') * pow26[s.length() - 1 - i] + postHash[i + 1]) % MOD;
        }

        for (int len = s.length() - 1; len >= 1; len--)
        {
            // s[0 ... len - 1] == s[s.length - len ... s.length - 1] ? 有可能存在哈希冲突
            if (prevHash[len - 1] == postHash[s.length() - len] &&
                    equal(s, 0, len - 1, s.length() - len, s.length() - 1))
                return s.substring(0, len);
        }

        return "";
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
