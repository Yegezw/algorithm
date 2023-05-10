package chapter4.section1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/intersection-of-two-arrays/">349 - 两个数组的交集</a>
 */
public class Intersection {

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) set.add(i);

        Set<Integer> res = new HashSet<>();
        for (int i : nums2) {
            if (set.contains(i)) res.add(i);
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println(Arrays.toString(intersection(nums1, nums2)));
    }
}
