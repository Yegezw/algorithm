package practice2_linked_list;

/**
 * <a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/">19 - 删除链表的倒数第 N 个结点</a>
 */
public class Solution5 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(-1, head);

        ListNode p = dummyHead;
        ListNode q = p;
        // 让 q 向后移动 n + 1 次
        for (int i = 1; i <= n + 1; i++) q = q.next;

        while (q != null) {
            p = p.next;
            q = q.next;
        }
        p.next = p.next.next;

        return dummyHead.next;
    }
}
