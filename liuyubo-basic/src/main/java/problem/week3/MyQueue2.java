package problem.week3;

import java.util.Stack;

/**
 * 232 - 用栈实现队列: https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * <p>需要保证最先进来的元素在栈顶</p>
 * <p>push(int x): O(1)</p>
 * <p>pop(): O(n)</p>
 * <p>top(): O(1)</p>
 * <p>empty(): O(1)</p>
 */
@SuppressWarnings("all")
public class MyQueue2 {

    private final Stack<Integer> stack;
    private int front;

    public MyQueue2() {
        stack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (stack.isEmpty()) front = x;
    }

    public int pop() {
        Stack<Integer> temp = new Stack<>();
        while (stack.size() != 1) {
            front = temp.peek();
            temp.push(temp.pop());
        }
        int pop = stack.pop();
        while (!temp.isEmpty()) stack.push(temp.pop());
        return pop;
    }

    public int peek() {
        return front;
    }

    public boolean empty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue1 stack = new MyQueue1();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }

}
