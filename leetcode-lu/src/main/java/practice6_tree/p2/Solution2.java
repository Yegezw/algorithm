package practice6_tree.p2;

import practice6_tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/">111. 二叉树的最小深度</a>
 */
public class Solution2 {

    /**
     * 后序遍历
     */
    public int minDepth1(TreeNode root) {
        if (root == null) return 0;

        int left = minDepth1(root.left);
        int right = minDepth1(root.right);

        if (left == 0) return right + 1;
        if (right == 0) return left + 1;
        return Math.min(left, right) + 1;
    }

    /**
     * 层序遍历
     */
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;

        int depth = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.remove();
                if (cur.left == null && cur.right == null) return depth; // 最小深度

                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }

        return depth;
    }
}
