package chapter3.work;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/find-all-anagrams-in-a-string/">438 - 找到字符串中所有字母异位词</a>
 */
@SuppressWarnings("all")
public class FindAnagrams {

    public static List<Integer> findAnagrams1(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s.length() < p.length()) return list;

        int[] freq_p = new int[26];
        for (char c : p.toCharArray()) freq_p[c - 'a']++;

        // s[l ... r] 是滑动窗口
        int l = 0;
        int r = l + p.length() - 1;
        int[] freq_s = new int[26];
        for (int i = l; i <= r; i++) freq_s[s.charAt(i) - 'a']++;
        if (same(freq_s, freq_p)) list.add(l);

        // 窗口的右边界可以继续扩展, 则循环继续
        while (r + 1 < s.length()) {
            freq_s[s.charAt(++r) - 'a']++;
            freq_s[s.charAt(l++) - 'a']--;
            if (same(freq_s, freq_p)) list.add(l);
        }

        return list;
    }

    public static List<Integer> findAnagrams2(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s.length() < p.length()) return list;

        int[] freq_p = new int[26];
        for (char c : p.toCharArray()) freq_p[c - 'a']++;

        // s[l ... r] 是滑动窗口
        int l = 0;
        int r = -1;
        int[] freq_s = new int[256];

        // 窗口的右边界可以继续扩展, 则循环继续
        while (r + 1 < s.length()) {
            if (r - l + 1 != p.length()) {
                freq_s[s.charAt(++r) - 'a']++;
                if (r - l + 1 == p.length() && same(freq_s, freq_p)) list.add(l);
            } else {
                freq_s[s.charAt(++r) - 'a']++;
                freq_s[s.charAt(l++) - 'a']--;
                if (same(freq_s, freq_p)) list.add(l);
            }
        }

        return list;
    }

    private static boolean same(int[] freq_s, int[] freq_p) {
        for (int i = 0; i < 26; i++) {
            if (freq_s[i] != freq_p[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(findAnagrams1(s, p));
        System.out.println(findAnagrams2(s, p));
    }
}
