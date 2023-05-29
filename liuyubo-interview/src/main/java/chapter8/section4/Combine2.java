package chapter8.section4;

import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/combinations/">77 - 组合</a>
 */
public class Combine2 {

    private static final List<List<Integer>> res = new LinkedList<>();
    private static final LinkedList<Integer> track = new LinkedList<>(); // 路径

    public static List<List<Integer>> combine(int n, int k) {
        res.clear();
        track.clear();

        backtrack(1, n, k);
        return res;
    }

    /**
     * 回溯算法框架
     */
    private static void backtrack(int start, int n, int k) {
        // 到达叶子节点, 将路径装入结果列表
        if (k == track.size()) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = start; i <= n; i++) {
            track.addLast(i);            // 做选择
            backtrack(i + 1, n, k); // 进入下一层回溯树
            track.removeLast();          // 取消选择
        }
    }

    public static void main(String[] args) {
        System.out.println(combine(4, 2));
    }
}
