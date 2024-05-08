package problem.week4;

import other.pojo.ListNode;

/**
 * <a href="https://leetcode-cn.com/problems/reverse-linked-list/">206 - 反转链表</a>
 */
public class ReserveLinkedList
{

    /**
     * <p>非递归实现
     * <p>最终状态如下
     * <p>cur、next -> null
     * <p>prev -> newHead
     */
    public static ListNode reverseList1(ListNode head)
    {
        ListNode prev = null;
        ListNode cur  = head;
        ListNode next;
        while (cur != null)
        {
            next     = cur.next;
            cur.next = prev;
            prev     = cur;
            cur      = next;
        }
        return prev;
    }

    /**
     * 递归: 反转以 head 为头的链表, 并返回新的头结点
     */
    public static ListNode reverseList2(ListNode head)
    {
        if (head == null || head.next == null) return head;

        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next      = null;

        return newHead;
    }

    public static void main(String[] args)
    {
        int[]    arr  = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(arr);
        System.out.println(head);

        ListNode res = reverseList2(head);
        System.out.println(res);
    }
}