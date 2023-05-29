package chapter6.section2;

import chapter6.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-postorder-traversal/">145 - 二叉树的后序遍历</a>
 */
public class PostorderTraversal {

    /**
     * 递归实现
     */
    public static List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postOrder(root, list);
        return list;
    }

    private static void postOrder(TreeNode node, List<Integer> list) {
        if (node == null) return;

        if (node.left != null) postOrder(node.left, list);
        if (node.right != null) postOrder(node.right, list);
        list.add(node.val);
    }

    private static class Command {
        public String s; // go, print
        public TreeNode node;

        public Command(String s, TreeNode node) {
            this.s = s;
            this.node = node;
        }
    }

    /**
     * 非递归实现
     */
    public static List<Integer> postorderTraversal2(TreeNode root) {
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
                stack.push(new Command("print", node));
                if (node.right != null) stack.push(new Command("go", node.right));
                if (node.left != null) stack.push(new Command("go", node.left));
            }
        }

        return list;
    }
}
