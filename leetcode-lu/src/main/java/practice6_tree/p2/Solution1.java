package practice6_tree.p2;

import practice6_tree.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/">104. 二叉树的最大深度</a>
 */
public class Solution1 {

    /**
     * 后序遍历
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
