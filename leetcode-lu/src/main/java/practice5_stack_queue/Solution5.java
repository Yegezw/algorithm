package practice5_stack_queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/evaluate-reverse-polish-notation/description/">150 - 逆波兰表达式求值</a>
 */
public class Solution5 {

    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            if (!isFh(token)) stack.addLast(Integer.valueOf(token));
            else {
                int v2 = stack.removeLast();
                int v1 = stack.removeLast();
                switch (token) {
                    case "+":
                        stack.addLast(v1 + v2);
                        break;
                    case "-":
                        stack.addLast(v1 - v2);
                        break;
                    case "*":
                        stack.addLast(v1 * v2);
                        break;
                    case "/":
                        stack.addLast(v1 / v2);
                        break;
                }
            }
        }

        return stack.removeLast();
    }

    private boolean isFh(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }
}
