package practice3_hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/two-sum/">1 - 两数之和</a>
 */
public class Solution4 {

    public int[] twoSum(int[] nums, int target) {
        // num : index
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // complement 补充
            if (map.containsKey(complement)) return new int[]{i, map.get(complement)};
            else map.put(nums[i], i);
        }

        throw new IllegalArgumentException("The input has no solution");
    }
}
