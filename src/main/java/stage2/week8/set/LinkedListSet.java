package stage2.week8.set;

import port.Set;
import stage1.week4.link.LinkedList;

/**
 * 基于链表 LinkedList 实现的 Set
 */
public class LinkedListSet<E> implements Set<E>
{

    private final LinkedList<E> list;

    public LinkedListSet()
    {
        list = new LinkedList<>();
    }

    @Override
    public void add(E e)
    {
        if (!list.contains(e)) list.addFirst(e);
    }

    @Override
    public void remove(E e)
    {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e)
    {
        return list.contains(e);
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
}
