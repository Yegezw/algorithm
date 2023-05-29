package chapter7.section5;

import chapter6.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/path-sum-iii/">437 - 路径总和 III</a>
 */
public class PathSum {

    /**
     * 在以 root 为根节点的二叉树中, 寻找和为 sum 的路径, 返回路径个数
     */
    public static int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;

        int res = findPath(root, sum);   // 包含当前 root 节点, 和为 sum 的路径
        res += pathSum(root.left, sum);  // 不包含当前 root 节点, 和为 sum 的路径
        res += pathSum(root.right, sum); // 不包含当前 root 节点, 和为 sum 的路径

        return res;
    }

    /**
     * 在以 node 为根节点的二叉树中, 寻找包含 node 的和为 sum 的路径, 返回路径个数
     */
    private static int findPath(TreeNode node, long sum) {
        if (node == null) return 0;

        int res = 0;
        if (node.val == sum) res++;
        res += findPath(node.left, sum - node.val);
        res += findPath(node.right, sum - node.val);

        return res;
    }

    public static void main(String[] args) {
        //         10
        //       /     \
        //     5        -3
        //    /  \        \
        //   3    2        11
        //  /  \   \
        // 3   -2   1
        TreeNode root = new TreeNode(10);

        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(-3);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(11);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(-2);
        TreeNode node8 = new TreeNode(1);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.right = node8;

        System.out.println(pathSum(root, 8)); // 3
    }
}
