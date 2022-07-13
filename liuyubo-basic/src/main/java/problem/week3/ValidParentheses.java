package problem.week3;

import stage1.week3.stack.ArrayStack;

/**
 * 20 - 有效的括号: https://leetcode-cn.com/problems/valid-parentheses/
 */
public class ValidParentheses {

    /**
     * 给定一个只包括 '('、')'、'{'、'}'、'['、']'的字符串 s, 判断字符串是否有效
     */
    public boolean isValid(String s) {
        ArrayStack<Character> stack = new ArrayStack<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(' || c == '{' || c == '[') stack.push(c);
            else {
                if (stack.isEmpty()) return false;
                Character topChar = stack.pop();

                if (topChar == '(' && c != ')') return false;
                if (topChar == '{' && c != '}') return false;
                if (topChar == '[' && c != ']') return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses s20 = new ValidParentheses();

        System.out.println(s20.isValid("(("));
        System.out.println(s20.isValid("((){}"));
        System.out.println(s20.isValid("[{()}[]]"));
    }

}
