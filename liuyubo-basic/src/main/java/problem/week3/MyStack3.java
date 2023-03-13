package problem.week3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode-cn.com/problems/implement-stack-using-queues/">225 - 用队列实现栈<a/>
 * <p>push(int x): O(n)
 * <p>pop(): O(1)
 * <p>top(): O(1)
 * <p>empty(): O(1)
 */
@SuppressWarnings("all")
public class MyStack3 {

    private final Queue<Integer> q;

    public MyStack3() {
        q = new LinkedList<>();
    }

    public void push(int x) {
        q.add(x);
        for (int i = 0; i < q.size() - 1; i++) q.add(q.remove());
    }

    public int pop() {
        return q.remove();
    }

    public int top() {
        return q.peek();
    }

    public boolean empty() {
        return q.isEmpty();
    }

    public static void main(String[] args) {
        MyStack2 stack = new MyStack2();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
