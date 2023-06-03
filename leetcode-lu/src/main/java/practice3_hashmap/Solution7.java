package practice3_hashmap;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/3sum/">15 - 三数之和</a>
 */
public class Solution7 {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        // nums[i] + nums[p1] + nums[p2] = 0
        // nums[i] <= nums[p1] <= nums[p2]
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) continue;
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int p1 = i + 1;
            int p2 = nums.length - 1;
            while (p1 < p2) {
                int v1 = nums[p1];
                int v2 = nums[p2];
                int sum = nums[i] + v1 + v2;

                if (sum < 0) {
                    while (p1 < p2 && nums[p1] == v1) p1++;
                } else if (sum > 0) {
                    while (p1 < p2 && nums[p2] == v2) p2--;
                } else {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], v1, v2)));
                    while (p1 < p2 && nums[p1] == v1) p1++;
                    while (p1 < p2 && nums[p2] == v2) p2--;
                }
            }
        }

        return res;
    }
}
