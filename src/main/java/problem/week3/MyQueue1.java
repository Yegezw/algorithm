package problem.week3;

import java.util.Stack;

/**
 * <a href="https://leetcode-cn.com/problems/implement-queue-using-stacks/">232 - 用栈实现队列<a/>
 * <p>push(int x): O(n)
 * <p>pop(): O(1)
 * <p>peek(): O(1)
 * <p>empty(): O(1)
 */
@SuppressWarnings("all")
public class MyQueue1 {

    private final Stack<Integer> stack;

    public MyQueue1() {
        stack = new Stack<>();
    }

    public void push(int x) {
        Stack<Integer> temp = new Stack<>();
        while (!stack.isEmpty()) temp.push(stack.pop());
        stack.push(x);
        while (!temp.isEmpty()) stack.push(temp.pop());
    }

    public int pop() {
        return stack.pop();
    }

    public int peek() {
        return stack.peek();
    }

    public boolean empty() {
        return stack.empty();
    }

    public static void main(String[] args) {
        MyQueue1 queue = new MyQueue1();
        for (int i = 0; i < 5; i++) {
            queue.push(i);
        }
        while (!queue.empty()) {
            System.out.println(queue.pop());
        }
    }
}
