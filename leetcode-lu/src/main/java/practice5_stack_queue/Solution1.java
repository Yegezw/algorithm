package practice5_stack_queue;

import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/implement-queue-using-stacks/description/">232 - 用栈实现队列</a>
 */
public class Solution1 {

    private Stack<Integer> stack1; // 用来添加元素
    private Stack<Integer> stack2; // 用来缓存部分已经排好的元素
    private int front; // 需要保证 front 是 stack1 中最先进来的元素

    public Solution1() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        if (stack1.isEmpty()) front = x;
        stack1.push(x);
    }

    // [123 -> 123]
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
}
