package chapter6.section2;

import chapter6.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-preorder-traversal/">144 - 二叉树的前序遍历</a>
 */
public class PreorderTraversal {

    /**
     * 递归实现
     */
    public static List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }

    private static void preOrder(TreeNode root, List<Integer> list) {
        if (root == null) return;

        list.add(root.val);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    /**
     * 非递归实现 1
     */
    public static List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return list;
    }

    private static class Command {
        public String s; // go, print
        public TreeNode node;

        public Command(String command, TreeNode node) {
            this.s = command;
            this.node = node;
        }
    }

    /**
     * 非递归实现 2
     */
    public static List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));

        while (!stack.isEmpty()) {
            Command command = stack.pop();
            String s = command.s;
            TreeNode node = command.node;

            if (s.equals("print")) list.add(node.val);
            else {
                if (node.right != null) stack.push(new Command("go", node.right));
                if (node.left != null) stack.push(new Command("go", node.left));
                stack.push(new Command("print", node));
            }
        }

        return list;
    }
}
