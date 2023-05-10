package chapter4.section8;

import java.util.TreeSet;

/**
 * <a href="https://leetcode.cn/problems/contains-duplicate-iii/">220 - 存在重复元素 III</a>
 */
public class ContainsNearbyAlmostDuplicate {

    public static boolean containsNearbyAlmostDuplicate1(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> set = new TreeSet<>(); // 窗口大小为 indexDiff + 1
        for (int i = 0; i < nums.length; i++) {
            // [min ... max] = [num - valueDiff ... num + valueDiff]
            int num = nums[i];
            long min = num - valueDiff;
            long max = num + valueDiff;

            Long ceiling = set.ceiling(min);
            if (ceiling != null && ceiling <= max) return true;
            else set.add((long) num);

            // 保持 set 中最多有 indexDiff 个元素
            if (set.size() == indexDiff + 1) set.remove((long) nums[i - indexDiff]);
        }

        return false;
    }

    public static boolean containsNearbyAlmostDuplicate2(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> set = new TreeSet<>(); // 窗口大小为 indexDiff + 1
        for (int i = 0; i < nums.length; i++) {
            // [min ... max] = [num - valueDiff ... num + valueDiff]
            int num = nums[i];
            long min = num - valueDiff;
            long max = num + valueDiff;

            Long floor = set.floor(max);
            if (floor != null && floor >= min) return true;
            else set.add((long) num);

            if (set.size() == indexDiff + 1) set.remove((long) nums[i - indexDiff]);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(containsNearbyAlmostDuplicate1(nums, 3, 0));
        System.out.println(containsNearbyAlmostDuplicate2(nums, 3, 0));
    }
}
