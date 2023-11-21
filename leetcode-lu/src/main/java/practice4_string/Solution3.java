package practice4_string;

/**
 * <a href="https://leetcode.cn/problems/ti-huan-kong-ge-lcof/">LCR 122. 路径加密</a>
 */
public class Solution3 {

    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '.') sb.append(" ");
            else sb.append(c);
        }
        return sb.toString();
    }
}
