package practice4_string;

/**
 * <a href="https://leetcode.cn/problems/reverse-string-ii/">541 - 反转字符串 II</a>
 */
public class Solution2 {

    public String reverseStr(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();

        // arr[i ... i + 2k - 1]
        for (int i = 0; i < n; i += 2 * k) {
            reverse(arr, i, Math.min(n - 1, i + k - 1));
        }

        return new String(arr);
    }

    /**
     * 反转 arr[p1 ... p2]
     */
    public void reverse(char[] arr, int p1, int p2) {
        char temp;
        while (p1 < p2) {
            temp = arr[p1];
            arr[p1] = arr[p2];
            arr[p2] = temp;
            p1++;
            p2--;
        }
    }
}
