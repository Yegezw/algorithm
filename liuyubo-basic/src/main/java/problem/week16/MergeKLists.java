package problem.week16;

import other.pojo.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.cn/problems/merge-k-sorted-lists/">23 - 合并 K 个升序链表</a>
 */
public class MergeKLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];

        // 最小堆
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode head : lists) {
            if (head != null) pq.add(head);
        }

        ListNode dummyHead = new ListNode();
        ListNode prev = dummyHead;
        while (!pq.isEmpty()) {
            ListNode node = pq.remove();
            prev.next = node;
            prev = prev.next;
            if (node.next != null) pq.add(node.next);
        }

        return dummyHead.next;
    }
}
