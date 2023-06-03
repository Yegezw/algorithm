package practice2_linked_list;

/**
 * <a href="https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/">面试题 02.07 - 链表相交</a>
 */
public class Solution6 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode h1 = headA;
        ListNode h2 = headB;

        while (h1 != h2) {
            h1 = h1 != null ? h1.next : headB;
            h2 = h2 != null ? h2.next : headA;
        }

        return h1;
    }
}
