package practice4_string;

/**
 * <a href="https://leetcode.cn/problems/repeated-substring-pattern/">459 - 重复的子字符串</a>
 */
@SuppressWarnings("all")
public class Solution7 {

    public boolean repeatedSubstringPattern1(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }

    public boolean repeatedSubstringPattern2(String s) {
        int n = s.length();
        int[] lps = getLPS(s);

        return lps[n - 1] != 0 && n % (n - lps[n - 1]) == 0;
    }

    private int[] getLPS(String t) {
        // lps[i] = length(t[0 ... i] max border)
        int[] lps = new int[t.length()];

        for (int i = 1; i < lps.length; i++) {
            int a = lps[i - 1];
            while (a > 0 && t.charAt(a) != t.charAt(i)) a = lps[a - 1];
            if (t.charAt(a) == t.charAt(i)) lps[i] = a + 1;
        }

        return lps;
    }
}
