package problem.week7;

/**
 * <a href="https://leetcode-cn.com/problems/binary-search/">704 - 二分查找</a>
 */
public class Search
{

    /**
     * 递归
     */
    public static int search1(int[] nums, int target)
    {
        return search1(nums, 0, nums.length - 1, target);
    }

    private static int search1(int[] nums, int l, int r, int target)
    {
        if (l > r) return -1;

        int mid = l + (r - l) / 2;
        if (nums[mid] == target) return mid;

        if (nums[mid] < target) return search1(nums, mid + 1, r, target);
        return search1(nums, l, mid - 1, target);
    }

    /**
     * 非递归
     */
    public static int search2(int[] nums, int target)
    {
        int l = 0;
        int r = nums.length - 1;
        int mid;

        // 在 arr[l, r] 中查找 target
        while (l <= r)
        {
            mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) l = mid + 1;
            else r = mid - 1;
        }

        return -1;
    }

    /**
     * 非递归
     */
    public static int search3(int[] arr, int target)
    {
        int l = 0;
        int r = arr.length;
        int mid;

        // 在 arr[l, r) 中查找 target
        while (l < r)
        {
            mid = l + (r - l) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) l = mid + 1;
            else r = mid;
        }

        return -1;
    }

    public static void main(String[] args)
    {
        int[] arr = {1, 3, 5, 7, 9};
        System.out.println(search1(arr, 9));
        System.out.println(search2(arr, 9));
        System.out.println(search3(arr, 9));
    }
}