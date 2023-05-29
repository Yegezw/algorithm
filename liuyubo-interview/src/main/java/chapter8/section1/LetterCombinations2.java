package chapter8.section1;

import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/letter-combinations-of-a-phone-number/">17 - 电话号码的字母组合</a>
 */
public class LetterCombinations2 {

    private static final String[] letterMap = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private static final List<String> res = new LinkedList<>();

    public static List<String> letterCombinations(String digits) {
        res.clear();
        if (digits == null || digits.equals("")) return res;

        StringBuilder track = new StringBuilder(); // 路径
        backtrack(digits, 0, track);
        return res;
    }

    /**
     * 回溯算法框架
     */
    private static void backtrack(String digits, int index, StringBuilder track) {
        // 到达叶子节点, 将路径装入结果列表
        if (track.length() == digits.length()) {
            res.add(track.toString());
            return;
        }

        char c = digits.charAt(index);
        String letters = letterMap[c - '0'];
        for (int i = 0; i < letters.length(); i++) {
            track.append(letters.charAt(i));              // 做选择
            backtrack(digits, index + 1, track);    // 进入下一层回溯树
            track.deleteCharAt(track.length() - 1); // 取消选择
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations(""));
        System.out.println(letterCombinations("23"));
    }
}
