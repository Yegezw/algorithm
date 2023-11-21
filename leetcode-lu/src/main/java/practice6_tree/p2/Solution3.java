package practice6_tree.p2;

import practice6_tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/symmetric-tree/description/">101. 对称二叉树</a>
 */
public class Solution3 {

    /**
     * 层序遍历
     */
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {
            TreeNode left = queue.remove();
            TreeNode right = queue.remove();

            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;

            queue.add(left.left);   // 左左
            queue.add(right.right); // 右右
            queue.add(left.right);  // 左右
            queue.add(right.left);  // 右左
        }

        return true;
    }

    /**
     * 递归
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;

        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;
        if (node1.val != node2.val) return false;

        return dfs(node1.left, node2.right) && dfs(node1.right, node2.left);
    }
}
