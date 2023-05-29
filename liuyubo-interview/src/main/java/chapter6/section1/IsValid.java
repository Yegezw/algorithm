package chapter6.section1;

import java.util.Stack;

public class IsValid {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(' || c == '{' || c == '[') stack.push(c);
            else {
                if (stack.isEmpty()) return false;
                char popC = stack.pop();
                if (c == ')' && popC != '(') return false;
                if (c == '}' && popC != '{') return false;
                if (c == ']' && popC != '[') return false;
            }
        }

        return stack.isEmpty();
    }
}
