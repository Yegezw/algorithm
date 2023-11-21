package practice6_tree.p1;

import practice6_tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-right-side-view/description/">199. 二叉树的右视图</a>
 */
public class Solution6 {

    public List<Integer> rightSideView(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return new ArrayList<>();

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

        List<Integer> res = new ArrayList<>();
        for (List<Integer> level : list) {
            res.add(level.get(level.size() - 1));
        }
        return res;
    }
}
