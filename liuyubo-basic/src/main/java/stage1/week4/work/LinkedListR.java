package stage1.week4.work;

import other.pojo.Pair;

/**
 * 递归链表: 所有操作采用递归来完成
 */
@SuppressWarnings("all")
public class LinkedListR<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head;
    private int size;

    public LinkedListR() {
        head = null;
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
        if (index < 0 || index > size) throw new RuntimeException("need 0 <= index <= size");

        head = add(head, index, e);
        size++;
    }

    /**
     * <p>以 node 为头结点的链表, 给它的 index 位置添加元素 e, 并返回新链表的头结点
     * <p>注意: 此函数并不维护 size
     */
    private Node add(Node node, int index, E e) {
        if (index == 0) return new Node(e, node);

        node.next = add(node.next, index - 1, e);
        return node;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 删除
     */
    public E remove(int index) {
        if (index < 0 || index >= size) throw new RuntimeException("need 0 <= index < size");

        Pair<Node, E> pair = remove(head, index);
        head = pair.getKey();
        size--;
        return pair.getValue();
    }

    /**
     * <p>以 node 为头结点的链表, 删除它 index 位置的元素, 并返回新链表的头结点和被删除的元素 e
     * <p>注意: 此函数并不维护 size
     */
    private Pair<Node, E> remove(Node node, int index) {
        if (index == 0) return new Pair<>(node.next, node.e);

        Pair<Node, E> pair = remove(node.next, index - 1);
        node.next = pair.getKey();

        return new Pair<>(node, pair.getValue());
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        head = removeElement(head, e);
    }

    /**
     * <p>以 node 为头结点的链表, 删除它中 "所有的" 元素 e, 并返回新链表的头结点
     * <p>注意: 此函数会维护 size
     */
    private Node removeElement(Node node, E e) {
        if (node == null) return null;

        node.next = removeElement(node.next, e); // "所有的" 体现在这里
        if (node.e.equals(e)) {
            node = node.next;
            size--;
        }
        return node;
    }

    /**
     * 修改
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) throw new RuntimeException("need 0 <= index < size");

        set(head, index, e);
    }

    /**
     * 以 node 为头结点的链表, 把它 index 位置的元素修改为 e
     */
    private void set(Node node, int index, E e) {
        if (index == 0) node.e = e;
        else set(node.next, index - 1, e);
    }

    /**
     * 查看
     */
    public E get(int index) {
        if (index < 0 || index >= size) throw new RuntimeException("need 0 <= index < size");

        return get(head, index);
    }

    /**
     * 以 node 为头结点的链表, 获得它 index 位置的元素
     */
    private E get(Node node, int index) {
        if (index == 0) return node.e;
        return get(node.next, index - 1);
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public boolean contains(E e) {
        return contains(head, e);
    }

    /**
     * 以 node 为头结点的链表, 查询它中否有元素 e
     */
    private boolean contains(Node node, E e) {
        if (node == null) return false;
        
        if (node.e.equals(e)) return true;
        return contains(node.next, e);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur).append("->");
        }
        sb.append("NULL");
        return sb.toString();
    }
}
