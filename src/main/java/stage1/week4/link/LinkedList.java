package stage1.week4.link;

/**
 * 用虚拟头结点 dummyHead 来简化添加、删除的操作
 */
@SuppressWarnings("all")
public class LinkedList<E>
{

    private class Node
    {
        public E    e;
        public Node next;

        public Node(E e, Node next)
        {
            this.e    = e;
            this.next = next;
        }

        public Node(E e)
        {
            this(e, null);
        }

        public Node()
        {
            this(null, null);
        }

        @Override
        public String toString()
        {
            return e.toString();
        }
    }

    private final Node dummyHead;
    private       int  size;

    public LinkedList()
    {
        dummyHead = new Node();
        size      = 0;
    }

    public int getSize()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * 添加
     */
    public void add(int index, E e)
    {
        if (index < 0 || index > size) throw new RuntimeException("need 0 <= index <= size");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) prev = prev.next;
        prev.next = new Node(e, prev.next);
        size++;
    }

    public void addFirst(E e)
    {
        add(0, e);
    }

    public void addLast(E e)
    {
        add(size, e);
    }

    /**
     * 删除
     */
    public E remove(int index)
    {
        if (index < 0 || index >= size) throw new RuntimeException("need 0 <= index < size");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) prev = prev.next;

        Node delNode = prev.next;
        prev.next    = delNode.next;
        delNode.next = null;
        size--;
        return delNode.e;
    }

    public E removeFirst()
    {
        return remove(0);
    }

    public E removeLast()
    {
        return remove(size - 1);
    }

    /**
     * 存在就删除
     */
    public void removeElement(E e)
    {
        Node prev = dummyHead;
        while (prev.next != null)
        {
            if (e.equals(prev.next.e))
            {
                prev.next = prev.next.next;
                size--;
            }
            else prev = prev.next;
        }
    }

    /**
     * 修改
     */
    public void set(int index, E e)
    {
        if (index < 0 || index >= size) throw new RuntimeException("need 0 <= index < size");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) cur = cur.next;
        cur.e = e;
    }

    /**
     * 查看
     */
    public E get(int index)
    {
        if (index < 0 || index >= size) throw new RuntimeException("need 0 <= index < size");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur.e;
    }

    public E getFirst()
    {
        return get(0);
    }

    public E getLast()
    {
        return get(size - 1);
    }

    public boolean contains(E e)
    {
        Node cur = dummyHead.next;
        while (cur != null)
        {
            if (cur.e.equals(e)) return true;
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (Node cur = dummyHead.next; cur != null; cur = cur.next)
        {
            sb.append(cur).append("->");
        }
        sb.append("NULL");
        return sb.toString();
    }
}
