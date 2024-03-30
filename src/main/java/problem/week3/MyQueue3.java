package problem.week3;

import java.util.Stack;

/**
 * <a href="https://leetcode-cn.com/problems/implement-queue-using-stacks/">232 - 用栈实现队列<a/>
 * <p>push(int x): O(1)
 * <p>pop(): O(1)
 * <p>peek(): O(1)
 * <p>empty(): O(1)
 */
@SuppressWarnings("all")
public class MyQueue3 {

    private Stack<Integer> stack1; // 用来添加元素
    private Stack<Integer> stack2; // 用来缓存部分已经排好的元素
    private int front; // 需要保证 front 是 stack1 中最先进来的元素

    public MyQueue3() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        if (stack1.isEmpty()) front = x;
        stack1.push(x);
    }

    public int pop() {
        if (!stack2.isEmpty()) return stack2.pop();
        while (stack1.size() != 1) stack2.push(stack1.pop());
        return stack1.pop();
    }

    public int peek() {
        if (!stack2.isEmpty()) return stack2.peek();
        return front;
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue3 queue = new MyQueue3();
        for (int i = 0; i < 5; i++) {
            queue.push(i);
        }
        while (!queue.empty()) {
            System.out.println(queue.pop());
        }
    }
}
