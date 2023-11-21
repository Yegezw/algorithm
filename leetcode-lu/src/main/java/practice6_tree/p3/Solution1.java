package practice6_tree.p3;

import practice6_tree.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/invert-binary-tree/">226. 翻转二叉树</a>
 */
public class Solution1 {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode l = invertTree(root.left);
        TreeNode r = invertTree(root.right);

        root.left = r;
        root.right = l;
        return root;
    }
}
