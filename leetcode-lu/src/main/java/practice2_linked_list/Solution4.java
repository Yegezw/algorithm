package practice2_linked_list;

/**
 * <a href="https://leetcode.cn/problems/swap-nodes-in-pairs/">24 - 两两交换链表中的节点</a>
 */
@SuppressWarnings("all")
class Solution4 {

    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyHead = new ListNode(-1, head);

        // 1 -> 2 -> 3 -> 4 -> 5 -> 6
        // 2 -> 1 -> 4 -> 3 -> 6 -> 5
        ListNode prev = dummyHead;
        ListNode node1 = head;
        ListNode node2 = head.next;
        ListNode next;
        while (node1 != null && node2 != null) {
            next = node2.next;
            node2.next = node1;
            node1.next = next;
            prev.next = node2;

            prev = node1;
            node1 = next;
            if (node1 != null) node2 = node1.next;
        }

        return dummyHead.next;
    }

    public ListNode swapPairs2(ListNode node) {
        if (node == null || node.next == null) return node;

        ListNode node1 = node;
        ListNode node2 = node.next;

        ListNode res = swapPairs2(node2.next);
        node2.next = node1;
        node1.next = res;

        return node2;
    }
}
