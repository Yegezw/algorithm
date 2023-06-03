package practice2_linked_list;

/**
 * <a href="https://leetcode.cn/problems/reverse-linked-list/">206 - 反转链表</a>
 */
class Solution3 {

    public ListNode reverseList1(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode next;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    public ListNode reverseList2(ListNode node) {
        if (node == null || node.next == null) return node;

        ListNode head = reverseList2(node.next);
        node.next.next = node;
        node.next = null;

        return head;
    }
}
