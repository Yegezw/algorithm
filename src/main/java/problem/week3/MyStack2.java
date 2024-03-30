package problem.week3;


import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode-cn.com/problems/implement-stack-using-queues/">225 - 用队列实现栈<a/>
 * <p>push(int x): O(1)
 * <p>pop(): O(n)
 * <p>top(): O(1)
 * <p>empty(): O(1)
 */
@SuppressWarnings("all")
public class MyStack2 {

    private final Queue<Integer> q;
    private int top;

    public MyStack2() {
        q = new LinkedList<>();
    }

    public void push(int x) {
        q.add(x);
        top = x;
    }

    public int pop() {
        for (int i = 0; i < q.size() - 1; i++) {
            top = q.remove();
            q.add(top);
        }
        return q.remove();
    }

    public int top() {
        return top;
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
