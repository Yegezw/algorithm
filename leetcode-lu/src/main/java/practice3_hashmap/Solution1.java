package practice3_hashmap;

/**
 * <a href="https://leetcode.cn/problems/valid-anagram/">242 - 有效的字母异位词</a>
 */
public class Solution1 {

    public boolean isAnagram1(String s, String t) {
        int[] freq_s = new int[26];
        int[] freq_t = new int[26];
        for (char c : s.toCharArray()) freq_s[c - 'a']++;
        for (char c : t.toCharArray()) freq_t[c - 'a']++;

        for (int i = 0; i < 26; i++) {
            if (freq_s[i] != freq_t[i]) return false;
        }
        return true;
    }

    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }

        for (int i : freq) {
            if (i != 0) return false;
        }
        return true;
    }
}
