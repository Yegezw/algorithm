package problem.week3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode-cn.com/problems/implement-stack-using-queues/">225 - 用队列实现栈<a/>
 * <p>push(int x): O(1)
 * <p>pop(): O(n)
 * <p>top(): O(n)
 * <p>empty(): O(1)
 */
@SuppressWarnings("all")
public class MyStack1
{

    private final Queue<Integer> q;

    public MyStack1()
    {
        q = new LinkedList<>();
    }

    public void push(int x)
    {
        q.add(x);
    }

    public int pop()
    {
        for (int i = 0; i < q.size() - 1; i++) q.add(q.remove());
        return q.remove();
    }

    public int top()
    {
        int pop = pop();
        push(pop);
        return pop;
    }

    public boolean empty()
    {
        return q.isEmpty();
    }

    public static void main(String[] args)
    {
        MyStack1 stack = new MyStack1();
        for (int i = 0; i < 5; i++)
        {
            stack.push(i);
        }
        while (!stack.empty())
        {
            System.out.println(stack.pop());
        }
    }
}
