package chapter8.section3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/permutations/">46 - 全排列</a>
 */
public class Permute2 {

    private static final List<List<Integer>> res = new ArrayList<>();
    private static boolean[] used;

    public static List<List<Integer>> permute(int[] nums) {
        res.clear();
        if (nums == null || nums.length == 0) return res;

        used = new boolean[nums.length];

        LinkedList<Integer> track = new LinkedList<>(); // 路径
        backtrack(nums, track);
        return res;
    }

    private static void backtrack(int[] nums, LinkedList<Integer> track) {
        // 到达叶子节点, 将路径装入结果列表
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;  // 排除不合法的选择

            track.add(nums[i]);     // 做选择
            used[i] = true;
            backtrack(nums, track); // 进入下一层回溯树

            track.removeLast();     // 取消选择
            used[i] = false;
        }
    }


    public static void main(String[] args) {
        System.out.println(permute(new int[]{0, 1}));
        System.out.println(permute(new int[]{1, 2, 3}));
    }
}
