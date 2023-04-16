package problem.week6;

import java.util.Arrays;

/**
 * <a href="https://leetcode-cn.com/problems/sort-colors/">75 - 颜色分类</a>
 * <p>整数 0、1 和 2 分别表示红色、白色和蓝色
 * <p>原地对它们进行排序, 使得相同颜色的元素相邻, 并按照红色、白色、蓝色顺序排列
 */
public class SortColors {

    /**
     * 三路快速排序
     */
    public static void sortColors(int[] nums) {
        int p1 = -1;
        int p2 = nums.length;
        
        int i = 0;
        while (i < p2) {
            if (nums[i] == 0) swap(nums, ++p1, i++);
            else if (nums[i] == 2) swap(nums, --p2, i);
            else i++;
        }
    }

    private static void swap(int[] nums, int a, int b) {
        int k = nums[a];
        nums[a] = nums[b];
        nums[b] = k;
    }
    
    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}