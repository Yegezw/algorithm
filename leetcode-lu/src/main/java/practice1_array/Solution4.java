package practice1_array;

import java.util.LinkedList;
import java.util.List;

public class Solution4 {

    /**
     * <a href="https://leetcode.cn/problems/minimum-size-subarray-sum/">209 - 长度最小的子数组</a>
     */
    public int minSubArrayLen1(int target, int[] nums) {
        int l = 0, r = -1; // [l ... r] 是滑动窗口
        int sum = 0;       // sum = 滑动窗口的和
        int res = nums.length + 1;

        // 窗口的左边界在数组范围内, 则循环继续
        while (l < nums.length) {
            if (r + 1 < nums.length && sum < target) sum += nums[++r];
            else sum -= nums[l++];

            if (sum >= target) res = Math.min(res, r - l + 1);
        }

        return res != nums.length + 1 ? res : 0;
    }

    /**
     * <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/">3 - 无重复字符的最长子串</a>
     */
    public int lengthOfLongestSubstring(String s) {
        int l = 0, r = -1;         // [0 ... r] 是滑动窗口
        int[] freq = new int[256]; // freq[c] = 滑动窗口中字符 c 的频率
        int res = 0;

        while (l < s.length()) {
            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) freq[s.charAt(++r)]++;
            else freq[s.charAt(l++)]--;

            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    /**
     * <a href="https://leetcode.cn/problems/find-all-anagrams-in-a-string/description/">438 - 找到字符串中所有字母异位词</a>
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList<>();
        if (s.length() < p.length()) return res;

        int[] freq_p = new int[26];    // freq_p 目标频率
        for (char c : p.toCharArray()) freq_p[c - 'a']++;

        int l = 0, r = p.length() - 1; // [l ... r] 是滑动窗口
        int[] freq_s = new int[26];    // freq_s[c] = 滑动窗口中字符 c 的频率

        for (int i = 0; i < p.length(); i++) freq_s[s.charAt(i) - 'a']++;
        if (same(freq_p, freq_s)) res.add(0);

        while (true) {
            if (r == s.length() - 1) break;

            r++;
            freq_s[s.charAt(r) - 'a']++;
            freq_s[s.charAt(l) - 'a']--;
            l++;
            if (same(freq_p, freq_s)) res.add(l);
        }

        return res;
    }

    private boolean same(int[] freq_p, int[] freq_s) {
        for (int i = 0; i < 26; i++) {
            if (freq_p[i] != freq_s[i]) return false;
        }
        return true;
    }

    /**
     * <a href="https://leetcode.cn/problems/minimum-window-substring/description/">76 - 最小覆盖子串</a>
     */
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        // 目标
        int[] freq_t = new int[256];
        for (char c : t.toCharArray()) freq_t[c]++;

        // 窗口 [l ... r]
        int l = 0, r = -1;
        int[] freq_s = new int[256];
        int count = 0; // 窗口中包含 t 中字符的数量

        int startIndex = -1;
        int minLength = s.length() + 1;

        while (l < s.length()) {
            if (r + 1 < s.length() && count < t.length()) {
                r++;
                char c = s.charAt(r);
                freq_s[c]++;
                if (freq_s[c] <= freq_t[c]) count++;
            } else {
                char c = s.charAt(l);
                l++;
                freq_s[c]--;
                if (freq_s[c] < freq_t[c]) count--;
            }

            if (count == t.length() && r - l + 1 < minLength) {
                startIndex = l;
                minLength = r - l + 1;
            }
        }

        if (startIndex == -1) return "";
        return s.substring(startIndex, startIndex + minLength);
    }
}
