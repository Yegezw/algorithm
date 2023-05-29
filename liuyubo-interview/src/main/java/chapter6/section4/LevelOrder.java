package chapter6.section4;

import chapter6.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-level-order-traversal/">102 - 二叉树的层序遍历</a>
 */
public class LevelOrder {

    private static class Pair<A, B> {
        public final A first;
        public final B second;

        public Pair(A key, B value) {
            this.first = key;
            this.second = value;
        }
    }

    public static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 0));

        while (!queue.isEmpty()) {
            TreeNode node = queue.peek().first;
            int level = queue.peek().second;
            queue.remove();

            if (level == res.size()) res.add(new ArrayList<>());
            res.get(level).add(node.val);

            if (node.left != null) queue.add(new Pair<>(node.left, level + 1));
            if (node.right != null) queue.add(new Pair<>(node.right, level + 1));
        }

        return res;
    }

    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                list.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            res.add(list);
        }

        return res;
    }
}
