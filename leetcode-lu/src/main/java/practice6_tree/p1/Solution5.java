package practice6_tree.p1;

import practice6_tree.TreeNode;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/description/">107. 二叉树的层序遍历 II</a>
 */
public class Solution5 {

    /**
     * 非递归
     */
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.remove();
                level.add(cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }

            list.add(level);
        }

        Collections.reverse(list);
        return list;
    }

    /**
     * 递归
     */
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;

        dfs(root, list, 0);
        Collections.reverse(list);

        return list;
    }

    private void dfs(TreeNode node, List<List<Integer>> list, int level) {
        if (node == null) return;

        if (level >= list.size()) list.add(new LinkedList<>());

        list.get(level).add(node.val);
        dfs(node.left, list, level + 1);
        dfs(node.right, list, level + 1);
    }
}
