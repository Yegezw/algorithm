package practice2_linked_list;

/**
 * <a href="https://leetcode.cn/problems/remove-linked-list-elements/">203 - 移除链表元素</a>
 */
public class Solution1 {

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1, head);

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) prev.next = prev.next.next;
            else prev = prev.next;
        }

        return dummyHead.next;
    }

    public static ListNode removeElements2(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements2(head.next, val);
        return head.val != val ? head : head.next;
    }
}
