package chapter3.work;

/**
 * <a href="https://leetcode.cn/problems/minimum-window-substring/">76 - 最小覆盖子串</a>
 */
@SuppressWarnings("all")
public class MinWindow {

    public static String minWindow1(String s, String t) {
        int[] freq_t = new int[256];
        for (char c : t.toCharArray()) freq_t[c]++;

        int startIndex = -1;
        int minLength = s.length() + 1;

        // s[l ... r] 是滑动窗口
        int l = 0;
        int r = -1;
        int[] freq_s = new int[256];
        int sCnt = 0; // sCnt 代表窗口中包含的 t 中字符的个数

        // 窗口的左边界在字符串范围内, 则循环继续
        while (l < s.length()) {
            if (r + 1 < s.length() && sCnt < t.length()) {
                char c = s.charAt(r + 1);
                freq_s[c]++;
                if (freq_s[c] <= freq_t[c]) sCnt++;
                r++;
            } else {
                if (sCnt == t.length() && r - l + 1 < minLength) {
                    minLength = r - l + 1;
                    startIndex = l;
                }

                char c = s.charAt(l);
                freq_s[c]--;
                if (freq_s[c] < freq_t[c]) sCnt--;
                l++;
            }
        }

        if (startIndex != -1) return s.substring(startIndex, startIndex + minLength);
        return "";
    }

    public static String minWindow2(String s, String t) {
        int[] freq_t = new int[256];
        for (char c : t.toCharArray()) freq_t[c]++;

        int startIndex = -1;
        int minLength = s.length() + 1;

        // s[l ... r] 是滑动窗口
        int l = 0;
        int r = -1;
        int sCnt = 0; // sCnt 代表窗口中包含的 t 中字符的个数
        int[] freq_s = new int[256];

        // 窗口的右边界可以继续扩展, 则循环继续
        while (r + 1 < s.length()) {
            while (r + 1 < s.length() && sCnt < t.length()) {
                char c = s.charAt(r + 1);
                freq_s[c]++;
                if (freq_s[c] <= freq_t[c]) sCnt++;
                r++;
            }

            while (sCnt == t.length()) {
                if (r - l + 1 < minLength) {
                    startIndex = l;
                    minLength = r - l + 1;
                }

                char c = s.charAt(l);
                freq_s[c]--;
                if (freq_s[c] < freq_t[c]) sCnt--;
                l++;
            }
        }

        if (startIndex != -1) return s.substring(startIndex, startIndex + minLength);
        return "";
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow1(s, t)); // BANC
        System.out.println(minWindow2(s, t)); // BANC
    }
}
