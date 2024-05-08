package stage1.week4.stack;

import port.Stack;
import stage1.week4.link.LinkedList;

/**
 * 链表栈
 */
@SuppressWarnings("all")
public class LinkedListStack<E> implements Stack<E>
{

    private final LinkedList<E> list;

    public LinkedListStack()
    {
        this.list = new LinkedList<>();
    }

    @Override
    public void push(E e)
    {
        list.addFirst(e);
    }

    @Override
    public E pop()
    {
        return list.removeFirst();
    }

    @Override
    public E peek()
    {
        return list.getFirst();
    }

    @Override
    public int getSize()
    {
        return list.getSize();
    }

    @Override
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedListStack: Top [");
        sb.append(list);
        sb.append(']');
        return sb.toString();
    }
}
