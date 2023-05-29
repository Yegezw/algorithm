package chapter5.section4;

import chapter5.ListNode;

/**
 * <a href="https://leetcode.cn/problems/swap-nodes-in-pairs/">24 - 两两交换链表中的节点</a>
 */
public class SwapPairs {

    public static ListNode swapPairs1(ListNode head) {
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

    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) return head;

        // 1 -> 2 -> 3 -> 4 -> 5 -> 6
        // 2 -> 1 -> 4 -> 3 -> 6 -> 5
        ListNode node = swapPairs2(head.next.next);
        ListNode newHead = head.next;
        newHead.next = head;
        head.next = node;

        return newHead;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        ListNode head = new ListNode(arr);
        System.out.println(head);

        ListNode node = swapPairs1(head);
        System.out.println(node);
    }
}
