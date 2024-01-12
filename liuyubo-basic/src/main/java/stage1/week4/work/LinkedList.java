package stage1.week4.work;

/**
 * 双链表
 */
public class LinkedList<E> {

    private class Node {
        public E e;
        public Node prev;
        public Node next;

        public Node() {
        }

        public Node(E e) {
            this.e = e;
            this.prev = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private final Node dummyHead;
    private final Node dummyTail;
    private int size;

    public LinkedList() {
        dummyHead = new Node();
        dummyTail = new Node();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加
     */
    public void add(int index, E e) {
        if (index == 0) addFirst(e);
        if (index == size) addLast(e);

        Node next = getNode(index);
        Node prev = next.prev;

        link(prev, new Node(e), next);
        size++;
    }

    public void addFirst(E e) {
        link(dummyHead, new Node(e), dummyHead.next);
        size++;
    }

    public void addLast(E e) {
        link(dummyTail.prev, new Node(e), dummyTail);
        size++;
    }

    /**
     * 删除
     */
    public E remove(int index) {
        if (isEmpty()) throw new RuntimeException("LinkedList is empty!");
        if (index == 0) return removeFirst();
        if (index == size) return removeLast();

        Node del = getNode(index);
        unLink(del.prev, del, del.next);
        size--;
        return del.e;
    }

    public E removeFirst() {
        if (isEmpty()) throw new RuntimeException("LinkedList is empty!");

        Node del = unLink(dummyHead, dummyHead.next, dummyHead.next.next);
        size--;
        return del.e;
    }

    public E removeLast() {
        if (isEmpty()) throw new RuntimeException("LinkedList is empty!");

        Node del = unLink(dummyTail.prev.prev, dummyTail.prev, dummyTail);
        size--;
        return del.e;
    }

    /**
     * 修改
     */
    public E set(int index, E e) {
        Node node = getNode(index);
        E oldValue = node.e;
        node.e = e;
        return oldValue;
    }

    /**
     * 查看
     */
    public E get(int index) {
        return getNode(index).e;
    }

    public E getFirst() {
        if (isEmpty()) throw new RuntimeException("LinkedList is empty!");
        return dummyHead.next.e;
    }

    public E getLast() {
        if (isEmpty()) throw new RuntimeException("LinkedList is empty!");
        return dummyTail.prev.e;
    }

    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != dummyTail) {
            if (cur.e.equals(e)) return true;
            cur = cur.next;
        }
        return false;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) throw new RuntimeException("need 0 <= index < size");

        Node cur;
        if (index < size / 2) {
            cur = dummyHead.next;
            for (int i = 0; i < index; i++) cur = cur.next;
        } else {
            index = size - 1 - index;
            cur = dummyTail.prev;
            for (int i = 0; i < index; i++) cur = cur.prev;
        }
        return cur;
    }

    private void link(Node prev, Node cur, Node next) {
        // prev <-> cur <-> next
        prev.next = cur;
        cur.prev = prev;
        cur.next = next;
        next.prev = cur;
    }

    private Node unLink(Node prev, Node del, Node next) {
        // prev <-> del <-> next
        prev.next = next;
        next.prev = prev;
        del.prev = del.next = null;
        return del;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node cur = dummyHead.next; cur != dummyTail; cur = cur.next) {
            sb.append(cur).append(" <-> ");
        }
        sb.append("NULL");
        return sb.toString();
    }
}
