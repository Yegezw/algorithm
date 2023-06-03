package practice3_hashmap;

/**
 * <a href="https://leetcode.cn/problems/ransom-note/">383 - 赎金信</a>
 */
public class Solution6 {

    public boolean canConstruct1(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;

        int[] freq_r = new int[26];
        int[] freq_m = new int[26];
        for (char c : ransomNote.toCharArray()) freq_r[c - 'a']++;
        for (char c : magazine.toCharArray()) freq_m[c - 'a']++;

        for (int i = 0; i < 26; i++) {
            if (freq_r[i] > freq_m[i]) return false;
        }
        return true;
    }

    public boolean canConstruct2(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;

        int[] freq = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            freq[magazine.charAt(i) - 'a']++;
            if (i < ransomNote.length()) freq[ransomNote.charAt(i) - 'a']--;
        }

        for (int i : freq) {
            if (i < 0) return false;
        }
        return true;
    }
}
