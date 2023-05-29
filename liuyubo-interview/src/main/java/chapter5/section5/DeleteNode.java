package chapter5.section5;

import chapter5.ListNode;

/**
 * <a href="https://leetcode.cn/problems/delete-node-in-a-linked-list/">237 - 删除链表中的节点</a>
 */
public class DeleteNode {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
