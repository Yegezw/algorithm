package chapter4.section7;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/contains-duplicate-ii/">219 - 存在重复元素 II</a>
 */
public class ContainsNearbyDuplicate {

    public static boolean containsNearbyDuplicate1(int[] nums, int k) {
        // nums[l ... r] 是滑动窗口
        int l = 0;
        int r = Math.min(l + k, nums.length - 1);
        Set<Integer> set = new HashSet<>();
        for (int i = l; i <= r; i++) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
        }

        while (r + 1 < nums.length) {
            set.remove(nums[l++]);
            r++;
            if (set.contains(nums[r])) return true;
            set.add(nums[r]);
        }

        return false;
    }

    public static boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> set = new HashSet<>(); // 窗口大小为 k + 1
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true;
            else set.add(nums[i]);

            // 保持 set 中最多有 k 个元素
            if (set.size() == k + 1) set.remove(nums[i - k]);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        System.out.println(containsNearbyDuplicate1(nums, k));
        System.out.println(containsNearbyDuplicate2(nums, k));
    }
}
