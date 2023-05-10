package chapter4.section2;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/intersection-of-two-arrays-ii/">350 - 两个数组的交集 II</a>
 */
public class Intersect {

    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) map.put(i, map.getOrDefault(i, 0) + 1);

        List<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (map.getOrDefault(i, 0) != 0) {
                list.add(i);
                map.put(i, map.get(i) - 1);
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println(Arrays.toString(intersect(nums1, nums2)));
    }
}
