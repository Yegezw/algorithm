package practice2_linked_list;

/**
 * <a href="https://leetcode.cn/problems/design-linked-list/">707 - 设计链表</a>
 */
@SuppressWarnings("all")
public class Solution2 {

    private class Node {
        int val;
        Node next;

        public Node() {
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    private Node dummyHead;
    int size;

    public Solution2() {
        dummyHead = new Node();
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size) return -1;

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) prev = prev.next;
        prev.next = new Node(val, prev.next);
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = prev.next.next;
        size--;
    }
}
