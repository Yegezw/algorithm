package problem.week3;

import java.util.Stack;

/**
 * <a href="https://leetcode-cn.com/problems/implement-queue-using-stacks/">232 - 用栈实现队列<a/>
 * <p>push(int x): O(1)
 * <p>pop(): O(n)
 * <p>peek(): O(1)
 * <p>empty(): O(1)
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
            front = stack.pop();
            temp.push(front);
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
        MyQueue2 queue = new MyQueue2();
        for (int i = 0; i < 5; i++) {
            queue.push(i);
        }
        while (!queue.empty()) {
            System.out.println(queue.pop());
        }
    }
}
