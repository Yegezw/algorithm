package practice.link;

import other.pojo.Pair;

/**
 * 递归链表("后续遍历")
 */
@SuppressWarnings("all")
public class LinkR<E> {

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

    public LinkR() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) throw new RuntimeException();

        head = add(head, index, e);
        size++;
    }

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

    public E remove(int index) {
        if (index < 0 || index >= size) throw new RuntimeException();

        Pair<Node, E> pair = remove(head, index);
        head = pair.getKey();
        size--;
        return pair.getValue();
    }

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

    private Node removeElement(Node node, E e) {
        if (node == null) return null;

        node.next = removeElement(node.next, e);
        if (node.e.equals(e)) {
            node = node.next;
            size--;
        }
        return node;
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) throw new RuntimeException();
        set(head, index, e);
    }

    private void set(Node node, int index, E e) {
        if (index == 0) {
            node.e = e;
            return;
        }
        set(node.next, index - 1, e);
    }

    public E get(int index) {
        if (index < 0 || index >= size) throw new RuntimeException();
        return get(head, index);
    }

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
