package practice6_tree.p1;

import practice6_tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-preorder-traversal/description/">144. 二叉树的前序遍历</a>
 */
@SuppressWarnings("all")
public class Solution1 {

    /**
     * 递归
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    private void preorder(TreeNode node, List<Integer> list) {
        if (node == null) return;

        list.add(node.val);
        preorder(node.left, list);
        preorder(node.right, list);
    }

    /**
     * 非递归
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop(); // 头 左 右]

            list.add(cur.val);
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }

        return list;
    }

    private class Command {
        public String s; // go, add
        public TreeNode node;

        public Command(String s, TreeNode node) {
            this.s = s;
            this.node = node;
        }
    }

    /**
     * 自己模拟栈
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Deque<Command> stack = new LinkedList<>();
        stack.push(new Command("go", root));
        while (!stack.isEmpty()) {
            Command command = stack.pop();
            String s = command.s;
            TreeNode node = command.node;

            if (s.equals("add")) list.add(node.val);
            else {
                if (node.right != null) stack.push(new Command("go", node.right));
                if (node.left != null) stack.push(new Command("go", node.left));
                stack.push(new Command("add", node));
            }
        }

        return list;
    }
}