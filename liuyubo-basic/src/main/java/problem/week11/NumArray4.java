package problem.week11;

import java.util.Arrays;

/**
 * <a href="https://leetcode-cn.com/problems/range-sum-query-mutable/">307 - 区域和检索 - 数组可修改</a>
 * <p>通过前缀和解决
 * <p>超出时间限制
 */
public class NumArray4 {

    private final int[] arr;
    private final int[] sum; // sum[i] 代表 arr[0...i] 的和

    public NumArray4(int[] nums) {
        arr = Arrays.copyOf(nums, nums.length);
        sum = new int[nums.length];

        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) sum[i] = sum[i - 1] + arr[i];
    }

    /**
     * O(n)
     */
    public void update(int index, int val) {
        int diff = val - arr[index];
        arr[index] = val;
        for (int i = index; i < sum.length; i++) sum[i] += diff;
    }

    public int sumRange(int left, int right) {
        if (left == 0) return sum[right];
        return sum[right] - sum[left - 1];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray4 x = new NumArray4(nums);

        System.out.println(x.sumRange(0, 2)); // 1

        x.update(1, 2);

        System.out.println(x.sumRange(0, 2)); // 3
        System.out.println(x.sumRange(3, 5)); // -4
        System.out.println(x.sumRange(0, 5)); // -1
    }
}
