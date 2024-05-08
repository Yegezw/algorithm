package problem.week14;

import java.util.Arrays;

/**
 * <a href="https://leetcode-cn.com/problems/sort-colors/">75 - 颜色分类</a>
 * <p>整数 0、1 和 2 分别表示红色、白色和蓝色
 * <p>对它们进行排序, 使得相同颜色的元素相邻, 并按照红色、白色、蓝色顺序排列
 */
@SuppressWarnings("all")
public class SortColors
{

    /**
     * 计数排序 1
     */
    public static void sortColors1(int[] nums)
    {
        int[] cnt = new int[3];
        for (int num : nums) cnt[num]++;

        // 0 [0, cnt0)
        // 1 [cnt0, cnt0 + cnt1)
        // 2 [cnt0 + cnt1, cnt0 + cnt1 + cnt2)
        for (int i = 0; i < cnt[0]; i++) nums[i] = 0;
        for (int i = cnt[0]; i < cnt[0] + cnt[1]; i++) nums[i] = 1;
        for (int i = cnt[0] + cnt[1]; i < cnt[0] + cnt[1] + cnt[2]; i++) nums[i] = 2;
    }

    /**
     * 计数排序 2
     */
    public static void sortColors2(int[] nums)
    {
        // nums 中的元素固定为 0, 1, 2
        // 处理元素取值范围是 [0, R) 的计数排序
        int   R   = 3;
        int[] cnt = new int[R];
        for (int num : nums) cnt[num]++;

        // [index[i], index[i + 1]) 区间的值为 i
        int[] index = new int[R + 1];
        for (int i = 0; i < R; i++)
        {
            // 右边界 = 左边界 + cnt[i]
            index[i + 1] = index[i] + cnt[i];
        }

        for (int i = 0; i + 1 < index.length; i++)
        {
            // [index[i], index[i + 1]) 区间的值为 i
            for (int j = index[i]; j < index[i + 1]; j++) nums[j] = i;
        }
    }

    public static void main(String[] args)
    {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors2(nums);
        System.out.println(Arrays.toString(nums));
    }
}