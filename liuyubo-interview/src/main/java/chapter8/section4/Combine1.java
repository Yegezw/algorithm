package chapter8.section4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/combinations/">77 - 组合</a>
 */
public class Combine1 {

    private static final List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> combine(int n, int k) {
        res.clear();

        LinkedList<Integer> track = new LinkedList<>();
        generateCombinations(n, k, 1, track);
        return res;
    }

    /**
     * 求解 C(n, k), 当前已经找到的组合存储在 track 中, 需要从 start 开始搜索新的元素
     */
    private static void generateCombinations(int n, int k, int start, LinkedList<Integer> track) {
        if (track.size() == k) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = start; i <= n; i++) {
            track.add(i);
            generateCombinations(n, k, i + 1, track);
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        System.out.println(combine(4, 2));
    }
}
