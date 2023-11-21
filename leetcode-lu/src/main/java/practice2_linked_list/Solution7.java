package practice2_linked_list;

/**
 * <a href="https://leetcode.cn/problems/linked-list-cycle-ii/">142 - 环形链表 II</a>
 */
@SuppressWarnings("all")
public class Solution7 {

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (true) {
            if (fast == null || fast.next == null) return null; // 无环
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }

        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}