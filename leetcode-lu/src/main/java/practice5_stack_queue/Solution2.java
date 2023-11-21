package practice5_stack_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/implement-stack-using-queues/description/">225 - 用队列实现栈</a>
 */
public class Solution2 {

    private Queue<Integer> queue;

    public Solution2() {
        queue = new LinkedList<>();
    }

    // 3214 -> 4321
    public void push(int x) {
        queue.add(x);
        for (int i = 0; i < queue.size() - 1; i++) queue.add(queue.remove());
    }

    public int pop() {
        return queue.remove();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
