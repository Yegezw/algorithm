package practice3_hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/4sum/">18 - 四数之和</a>
 */
public class Solution8 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        // nums[i] + nums[j] + nums[p1] + nums[p2] = target
        // nums[i] <= nums[j] <= nums[p1] <= nums[p2]
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            // 提前终止
            if ((long) 4 * nums[i] > target) break;
            if ((long) nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) continue;

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                // 提前终止
                if ((long) 3 * nums[j] + nums[i] > target) break;
                if ((long) nums[i] + nums[j] + nums[n - 1] + nums[n - 2] < target) continue;

                int p1 = j + 1;
                int p2 = nums.length - 1;
                while (p1 < p2) {
                    long v1 = nums[p1];
                    long v2 = nums[p2];
                    long sum = nums[i] + nums[j] + v1 + v2;

                    if (sum < target) {
                        while (p1 < p2 && nums[p1] == v1) p1++;
                    } else if (sum > target) {
                        while (p1 < p2 && nums[p2] == v2) p2--;
                    } else {
                        res.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], (int) v1, (int) v2)));
                        if (nums[p1] == nums[p2]) break; // 提前终止
                        while (p1 < p2 && nums[p1] == v1) p1++;
                        while (p1 < p2 && nums[p2] == v2) p2--;
                    }
                }
            }
        }

        return res;
    }
}
