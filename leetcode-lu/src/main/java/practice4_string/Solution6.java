package practice4_string;

/**
 * <a href="https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/">28 - 找出字符串中第一个匹配项的下标</a>
 */
@SuppressWarnings("all")
public class Solution6 {

    public int strStr(String s, String t) {
        if (t.length() == 0) return 0;
        if (s.length() < t.length()) return -1;

        int[] lps = getLPS(t);

        int si = 0; // sIndex
        int ti = 0; // tIndex
        while (si < s.length()) {
            if (s.charAt(si) == t.charAt(ti)) {
                si++;
                ti++;
                if (ti == t.length()) return si - t.length();
            }
            else if (ti > 0) ti = lps[ti - 1];
            else si++;
        }

        return -1;
    }

    private int[] getLPS(String t) {
        int[] lps = new int[t.length()];

        for (int i = 1; i < lps.length; i++) {
            int a = lps[i - 1];
            while (a > 0 && t.charAt(a) != t.charAt(i)) a = lps[a - 1];
            if (t.charAt(a) == t.charAt(i)) lps[i] = a + 1;
        }

        return lps;
    }
}