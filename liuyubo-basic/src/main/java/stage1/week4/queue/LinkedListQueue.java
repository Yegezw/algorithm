package stage1.week4.queue;

import port.Queue;

/**
 * <p>双指针: 头、尾指针, 出队用 head, 入队用 tail
 * <p>入队要考虑: 0 -> 1
 * <p>出队要考虑: 1 -> 0
 */
@SuppressWarnings("all")
public class LinkedListQueue<E> implements Queue<E> {

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
    private Node tail;
    private int size;

    public LinkedListQueue() {
        head = tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) head = tail = new Node(e);
        else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        
        Node retNode = head;
        head = retNode.next;
        retNode.next = null;
        size--;
        if (head == null) tail = null;
        return retNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedListQueue: Front [");
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur).append("->");
        }
        sb.append("NULL] Tail");
        return sb.toString();
    }
}
