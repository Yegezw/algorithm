package chapter5.section6;

import chapter5.ListNode;

/**
 * <a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/">19 - 删除链表的倒数第 N 个结点</a>
 */
public class RemoveNthFromEnd {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(-1, head);

        ListNode p = dummyHead;
        ListNode q = p;
        // 让 q 向后移动 n + 1 次
        for (int i = 0; i < n + 1; i++) {
            if (q == null) return head;
            q = q.next;
        }

        while (q != null) {
            p = p.next;
            q = q.next;
        }

        p.next = p.next.next;
        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(arr);

        ListNode node = removeNthFromEnd(head, 2);
        System.out.println(node);
    }
}
