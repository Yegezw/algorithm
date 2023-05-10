package chapter3.section8;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/">3 - 无重复字符的最长子串</a>
 */
@SuppressWarnings("all")
public class LengthOfLongestSubstring {

    public static int lengthOfLongestSubstring1(String s) {
        // s[l ... r] 是滑动窗口
        int l = 0;
        int r = -1;
        int[] freq = new int[256]; // freq[i] 代表字符 i 出现的频率
        int res = 0;

        // 窗口的左边界在字符串范围内, 则循环继续
        while (l < s.length()) {
            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) freq[s.charAt(++r)]++;
            else freq[s.charAt(l++)]--;

            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    public static int lengthOfLongestSubstring2(String s) {
        // s[l ... r] 是滑动窗口
        int l = 0;
        int r = -1;
        int[] index = new int[256]; // index[i] 代表字符 i 在 s 中的索引
        Arrays.fill(index, -1);
        int res = 0;

        // 窗口的左边界在字符串范围内, 则循环继续
        while (l < s.length()) {
            if (r + 1 < s.length() && index[s.charAt(r + 1)] == -1) index[s.charAt(++r)] = r;
            else index[s.charAt(l++)] = -1;

            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    public static int lengthOfLongestSubstring3(String s) {
        // s[l ... r] 是滑动窗口
        int l = 0;
        int r = -1;
        int[] index = new int[256]; // index[i] 代表字符 i 在 s 中的索引
        Arrays.fill(index, -1);
        int res = 0;

        // 窗口的右边界可以继续扩展, 则循环继续
        while (r + 1 < s.length()) {
            // r 用于扩展窗口
            while (r + 1 < s.length() && index[s.charAt(r + 1)] == -1) index[s.charAt(++r)] = r;
            res = Math.max(res, r - l + 1);

            // l 用于缩小窗口
            if (r + 1 < s.length()) {
                int i;
                for (i = l; i <= index[s.charAt(r + 1)]; i++) index[s.charAt(i)] = -1;
                l = i;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring1("abcabcbb"));
        System.out.println(lengthOfLongestSubstring2("abcabcbb"));
        System.out.println(lengthOfLongestSubstring3("abcabcbb"));
    }
}
