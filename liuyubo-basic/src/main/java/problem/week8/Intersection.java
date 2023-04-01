package problem.week8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * <a href="https://leetcode-cn.com/problems/intersection-of-two-arrays/">349 - 两个数组的交集</a>
 */
public class Intersection {

    public static int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums1) set.add(num);

        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                list.add(num);
                set.remove(num);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) res[i] = list.get(i);
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] res = intersection(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }
}
