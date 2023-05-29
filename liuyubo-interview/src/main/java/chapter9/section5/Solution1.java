package chapter9.section5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 0 - 1 背包问题
 */
@SuppressWarnings("all")
public class Solution1 {

    private static class Node {

        public int index;
        public boolean b;  // w[index] 要不要?
        public int w;
        public int v;

        public Node left;  // w[index + 1] 要
        public Node right; // w[index + 1] 不要

        public Node() {
        }

        public Node(int index, boolean b, int w, int v) {
            this.index = index;
            this.b = b;
            this.w = w;
            this.v = v;
        }
    }

    private static Node root;

    /**
     * w[i] 代表重量, v[i] 代表价值, C 代表背包容量
     */
    public static int knapsack01(int[] w, int[] v, int C) {
        if (w == null || w.length == 0 || v == null || v.length == 0) return 0;
        if (w.length != v.length) return 0;
        root = new Node();

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        for (int i = 0; i < w.length; i++) {
            List<Node> level = new ArrayList<>(); // 每一层的节点
            int size = queue.size();
            for (int j = 0; j < size; j++) level.add(queue.remove());

            for (Node node : level) {
                node.left = new Node(i, true, node.w + w[i], node.v + v[i]);
                node.right = new Node(i, false, node.w, node.v);
                queue.add(node.left);
                queue.add(node.right);
            }
        }

        List<Node> allNode = new ArrayList<>();
        perOrder(root, allNode);

        int res = -1;
        for (Node node : allNode) {
            if (node.w <= C) res = Math.max(res, node.v);
        }

        return res;
    }

    private static void perOrder(Node node, List<Node> list) {
        if (node == null) return;

        list.add(node);
        perOrder(node.left, list);
        perOrder(node.right, list);
    }

    public static void main(String[] args) {
        int[] w = {1, 2, 3};
        int[] v = {6, 10, 12};
        System.out.println(knapsack01(w, v, 5)); // 22
    }
}
