package chapter7.section4;

import chapter6.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-paths/">257 - 二叉树的所有路径</a>
 */
public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        if (root.left == null && root.right == null) {
            res.add("" + root.val);
            return res;
        }

        for (String s : binaryTreePaths(root.left)) res.add(root.val + "->" + s);
        for (String s : binaryTreePaths(root.right)) res.add(root.val + "->" + s);

        return res;
    }
}
