package practice6_tree.p1;

import practice6_tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-postorder-traversal/description/">145. 二叉树的后序遍历</a>
 */
@SuppressWarnings("all")
public class Solution3 {

    /**
     * 递归
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    private void postorder(TreeNode node, List<Integer> list) {
        if (node == null) return;

        postorder(node.left, list);
        postorder(node.right, list);
        list.add(node.val);
    }

    /**
     * 非递归
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Deque<TreeNode> stack1 = new LinkedList<>();
        Deque<TreeNode> stack2 = new LinkedList<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode cur = stack1.pop(); // 头 右 左]

            stack2.push(cur);
            if (cur.left != null) stack1.push(cur.left);
            if (cur.right != null) stack1.push(cur.right);
        }
        while (!stack2.isEmpty()) list.add(stack2.pop().val);

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
    public List<Integer> postorderTraversal3(TreeNode root) {
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
                stack.push(new Command("add", node));
                if (node.right != null) stack.push(new Command("go", node.right));
                if (node.left != null) stack.push(new Command("go", node.left));
            }
        }

        return list;
    }
}
