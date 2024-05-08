package problem.week3;

import stage1.week3.stack.ArrayStack;

/**
 * <a href="https://leetcode-cn.com/problems/valid-parentheses/">20 - 有效的括号</a>
 */
public class ValidParentheses
{

    /**
     * 给定一个只包括 '('、')'、'{'、'}'、'['、']'的字符串 s, 判断字符串是否有效
     */
    public boolean isValid(String s)
    {
        ArrayStack<Character> stack = new ArrayStack<>();
        char[]                chars = s.toCharArray();
        for (char c : chars)
        {
            if (c == '(' || c == '{' || c == '[') stack.push(c);
            else
            {
                if (stack.isEmpty()) return false;
                Character topChar = stack.pop();
                if (topChar == '(' && c != ')') return false;
                if (topChar == '{' && c != '}') return false;
                if (topChar == '[' && c != ']') return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args)
    {
        ValidParentheses test = new ValidParentheses();

        System.out.println(test.isValid("(("));
        System.out.println(test.isValid("((){}"));
        System.out.println(test.isValid("[{()}[]]"));
    }
}
