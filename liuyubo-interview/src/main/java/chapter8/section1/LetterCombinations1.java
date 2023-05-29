package chapter8.section1;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/letter-combinations-of-a-phone-number/">17 - 电话号码的字母组合</a>
 */
public class LetterCombinations1 {

    private static final String[] letterMap = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private static final List<String> res = new ArrayList<>();

    public static List<String> letterCombinations(String digits) {
        res.clear();
        if (digits == null || digits.equals("")) return res;

        findCombination(digits, 0, "");
        return res;
    }

    /**
     * <p>s 中保存了 digits[0 ... index - 1] 翻译得到的一个字母字符串
     * <p>寻找和 digits[index] 匹配的字母, 获得 digits[0 ... index] 翻译得到的解
     */
    private static void findCombination(String digits, int index, String s) {
        if (index == digits.length()) {
            res.add(s);
            return;
        }

        char c = digits.charAt(index);
        String letters = letterMap[c - '0'];
        for (int i = 0; i < letters.length(); i++) {
            findCombination(digits, index + 1, s + letters.charAt(i));
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations(""));
        System.out.println(letterCombinations("23"));
    }
}
