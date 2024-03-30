package problem.week15;

/**
 * <a href="https://leetcode-cn.com/problems/longest-happy-prefix/">1392 - 最长快乐前缀</a>
 */
@SuppressWarnings("all")
public class LongestPrefix2 {

    public String longestPrefix(String s) {
        int[] lps = getLPS(s);

        int len = lps[s.length() - 1];
        return s.substring(0, len);
    }

    private int[] getLPS(String t) {
        // border: 即是前缀又是后缀的子串(非自身、非空)
        // lps[i] 代表 t[0 ... i] 最长的 border 的长度
        int[] lps = new int[t.length()];

        lps[0] = 0;
        for (int i = 1; i < lps.length; i++) {
            int a = lps[i - 1]; // a 代表 t[0 ... i - 1] 最长的 border 的长度

            while (a > 0 && t.charAt(a) != t.charAt(i)) {
                a = lps[a - 1]; // a 代表 t[0 ... i - 1] 次长的 border 的长度
            }

            if (t.charAt(a) == t.charAt(i)) lps[i] = a + 1;
        }

        return lps;
    }
}
