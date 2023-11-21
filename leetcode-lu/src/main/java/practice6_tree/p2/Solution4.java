package practice6_tree.p2;

import practice6_tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/count-complete-tree-nodes/description/">222. 完全二叉树的节点个数</a>
 */
public class Solution4 {

    /**
     * 二叉树的节点个数: 递归
     */
    public int countNodes1(TreeNode root) {
        if (root == null) return 0;
        return countNodes1(root.left) + countNodes1(root.right) + 1;
    }

    /**
     * 二叉树的节点个数: 层序遍历
     */
    public int countNodes2(TreeNode root) {
        if (root == null) return 0;
        int count = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            count += size;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.remove();
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }

        return count;
    }

    /**
     * 完全二叉树的节点个数
     */
    public int countNodes3(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = leftDepth(root);
        int rightDepth = rightDepth(root);

        if (leftDepth == rightDepth) return (int) (Math.pow(2, leftDepth) - 1);
        return countNodes3(root.left) + countNodes3(root.right) + 1;
    }

    public int leftDepth(TreeNode node) {
        if (node == null) return 0;
        return leftDepth(node.left) + 1;
    }

    private int rightDepth(TreeNode node) {
        if (node == null) return 0;
        return rightDepth(node.right) + 1;
    }
}
