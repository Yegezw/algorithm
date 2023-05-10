package chapter3.section3;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/move-zeroes/description/">283 - 移动零</a>
 */
@SuppressWarnings("all")
public class MoveZeroes {

    /**
     * <p>时间复杂度: O(N)
     * <p>空间复杂度: O(N)
     */
    public static void moveZeroes1(int[] nums) {
        int[] temp = new int[nums.length];
        int index = 0;
        for (int num : nums) {
            if (num != 0) temp[index++] = num;
        }
        System.arraycopy(temp, 0, nums, 0, temp.length);
    }

    /**
     * <p>时间复杂度: O(N)
     * <p>空间复杂度: O(1)
     */
    public static void moveZeroes2(int[] nums) {
        // [0 ... p) 存放的都是非 0 元素
        int p = 0;
        for (int num : nums) {
            if (num != 0) nums[p++] = num;
        }
        Arrays.fill(nums, p, nums.length, 0);
    }

    /**
     * <p>时间复杂度: O(N)
     * <p>空间复杂度: O(1)
     */
    public static void moveZeroes3(int[] nums) {
        // [0 ... p) 存放的都是非 0 元素
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (p != i) swap(nums, p++, i);
                else p++;
            }
        }
    }

    private static void swap(int[] nums, int a, int b) {
        int k = nums[a];
        nums[a] = nums[b];
        nums[b] = k;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};

        // moveZeroes1(nums);
        // moveZeroes2(nums);
        moveZeroes3(nums);

        System.out.println(Arrays.toString(nums));
    }
}
