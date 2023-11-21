package practice4_string;

/**
 * <a href="https://leetcode.cn/problems/reverse-string/">344 - 反转字符串</a>
 */
public class Solution1 {

    public void reverseString(char[] s) {
        int p1 = 0;
        int p2 = s.length - 1;

        char temp;
        while (p1 < p2) {
            temp = s[p1];
            s[p1] = s[p2];
            s[p2] = temp;
            p1++;
            p2--;
        }
    }
}
