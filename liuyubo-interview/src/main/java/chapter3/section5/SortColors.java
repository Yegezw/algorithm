package chapter3.section5;

import java.util.Arrays;

/**
 * <a href="https://leetcode-cn.com/problems/sort-colors/">75 - 颜色分类</a>
 * <p>整数 0、1 和 2 分别表示红色、白色和蓝色
 * <p>原地对它们进行排序, 使得相同颜色的元素相邻, 并按照红色、白色、蓝色顺序排列
 */
public class SortColors {

    /**
     * <p>计数排序思路
     * <p>时间复杂度: O(N)
     * <p>空间复杂度: O(R), R 为元素的取值范围, 在这里 R = 3
     */
    public static void sortColors1(int[] nums) {
        // nums 中的元素固定为 0, 1, 2
        // 处理元素取值范围是 [0, R) 的计数排序
        int R = 3;
        int[] cnt = new int[R];
        for (int num : nums) cnt[num]++;

        // [index[i], index[i + 1]) 区间的值为 i
        int[] index = new int[R + 1];
        for (int i = 0; i < R; i++) {
            // 右边界 = 左边界 + cnt[i]
            index[i + 1] = index[i] + cnt[i];
        }

        for (int i = 0; i + 1 < index.length; i++) {
            // [index[i], index[i + 1]) 区间的值为 i
            for (int j = index[i]; j < index[i + 1]; j++) nums[j] = i;
        }
    }

    /**
     * <p>三路快速排思路
     * <p>时间复杂度: O(N)
     * <p>空间复杂度: O(1)
     */
    public static void sortColors2(int[] nums) {
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
        // sortColors1(nums);
        sortColors2(nums);
        System.out.println(Arrays.toString(nums));
    }
}
