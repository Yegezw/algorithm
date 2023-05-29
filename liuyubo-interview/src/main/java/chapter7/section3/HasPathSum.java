package chapter7.section3;

import chapter6.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/path-sum/">112 - 路径总和</a>
 */
public class HasPathSum {
 
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return targetSum == root.val; // 叶子节点

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}
