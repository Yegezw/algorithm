package practice4_string;

/**
 * <a href="https://leetcode.cn/problems/reverse-words-in-a-string/">151 - 反转字符串中的单词</a>
 */
public class Solution4 {

    public String reverseWords(String s) {
        String[] arr = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = arr.length - 1; i >= 0; i--) {
            String word = arr[i];

            if (word.equals("")) continue;

            sb.append(word);
            if (i != 0) sb.append(" ");
        }

        return sb.toString();
    }
}