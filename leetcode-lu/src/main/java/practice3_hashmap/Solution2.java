package practice3_hashmap;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/intersection-of-two-arrays/">349 - 两个数组的交集</a>
 */
public class Solution2 {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) set.add(i);

        Set<Integer> res = new HashSet<>();
        for (int i : nums2) {
            if (set.contains(i)) res.add(i);
        }

        int[] ret = new int[res.size()];
        int i = 0;
        for (int num : res) ret[i++] = num;
        return ret;
    }
}
