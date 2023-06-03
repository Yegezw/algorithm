package practice2_linked_list;

/**
 * 针对 leetcode 上关于链表的题目
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this(val, null);
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Arr is empty.");

        this.val = arr[0];
        ListNode cur = this;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            sb.append(cur.val).append("->");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }
}
