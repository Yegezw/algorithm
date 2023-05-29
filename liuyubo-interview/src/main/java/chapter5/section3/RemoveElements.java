package chapter5.section3;

import chapter5.ListNode;

/**
 * <a href="https://leetcode-cn.com/problems/remove-linked-list-elements/">203 - 移除链表元素</a>
 */
@SuppressWarnings("all")
public class RemoveElements {

    /**
     * 不使用 dummyHead
     */
    public static ListNode removeElements1(ListNode head, int val) {
        // 1.先删除符合条件的头结点
        while (head != null && head.val == val) head = head.next;
        if (head == null) return null;

        // 2.再删除其他节点
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) prev.next = prev.next.next;
            else prev = prev.next;
        }

        return head;
    }

    /**
     * 使用 dummyHead
     */
    public static ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1, head);

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) prev.next = prev.next.next;
            else prev = prev.next;
        }

        return dummyHead.next;
    }

    /**
     * 递归解决问题
     */
    public static ListNode removeElements3(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements3(head.next, val);
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(arr);
        System.out.println(head);

        ListNode h = removeElements3(head, 6);
        System.out.println(h);
    }
}
