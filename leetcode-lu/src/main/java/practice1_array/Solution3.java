package practice1_array;

/**
 * <a href="https://leetcode.cn/problems/squares-of-a-sorted-array/">977 - 有序数组的平方</a>
 */
public class Solution3 {

    public int[] sortedSquares(int[] nums) {
        int[] arr = new int[nums.length];
        int i = arr.length - 1;

        // 合并两个降序数组, 得到一个升序数组
        int p1 = 0;
        int p2 = nums.length - 1;
        while (p1 <= p2) {
            if (Math.abs(nums[p1]) > Math.abs(nums[p2])) {
                arr[i--] = nums[p1] * nums[p1];
                p1++;
            } else {
                arr[i--] = nums[p2] * nums[p2];
                p2--;
            }
        }

        return arr;
    }
}
