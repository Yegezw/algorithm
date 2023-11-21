package practice5_stack_queue;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/valid-parentheses/description/">20 - 有效的括号</a>
 */
public class Solution3 {

    public boolean isValid(String s) {
        char[] arr = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : arr) {
            if (c == '(' || c == '[' || c == '{') stack.addLast(c);
            else {
                if (stack.isEmpty()) return false;
                char top = stack.removeLast();
                if (top == '(' && c != ')') return false;
                if (top == '[' && c != ']') return false;
                if (top == '{' && c != '}') return false;
            }
        }

        return stack.isEmpty();
    }
}
