package chapter8.section3;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/permutations/">46 - 全排列</a>
 */
public class Permute1 {

    private static final List<List<Integer>> res = new ArrayList<>();
    private static boolean[] used;

    public static List<List<Integer>> permute(int[] nums) {
        res.clear();
        if (nums == null || nums.length == 0) return res;

        used = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) used[i] = false;

        generatePermutation(nums, 0, new ArrayList<>());
        return res;
    }

    /**
     * <p>p 中保存了一个有 index 个元素的排列
     * <p>向这个排列的末尾添加第 index + 1 个元素, 获得一个有 index + 1 个元素的排列
     */
    private static void generatePermutation(int[] nums, int index, ArrayList<Integer> p) {
        if (index == nums.length) {
            res.add(new ArrayList<>(p));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            p.add(nums[i]);
            used[i] = true;
            generatePermutation(nums, index + 1, p);

            p.remove(p.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
    }
}
