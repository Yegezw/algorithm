package chapter7.section6;

import chapter6.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/">235 - 二叉搜索树的最近公共祖先</a>
 */
@SuppressWarnings("all")
public class LowestCommonAncestor {

    /**
     * 只要 p 和 q 不在 root 的同侧, 那么 root 就是最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        if (p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.left, p, q);
        return root;
    }
}
