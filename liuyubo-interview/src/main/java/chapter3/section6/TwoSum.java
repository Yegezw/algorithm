package chapter3.section6;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/">167 - 两数之和 II - 输入有序数组</a>
 */
public class TwoSum {

    public static int[] twoSum1(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int complement = target - numbers[i];
            int res = binarySearch(numbers, i + 1, numbers.length - 1, complement);
            if (res != -1) return new int[]{i + 1, res + 1};
        }
        return new int[]{-1, -1};
    }

    private static int binarySearch(int[] arr, int l, int r, int target) {
        int mid;

        while (l <= r) {
            mid = l + (r - l) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] > target) r = mid - 1;
            else l = mid + 1;
        }

        return -1;
    }

    public static int[] twoSum2(int[] numbers, int target) {
        int p1 = 0;
        int p2 = numbers.length - 1;
        while (p1 < p2) {
            int sum = numbers[p1] + numbers[p2];
            if (sum == target) return new int[]{p1 + 1, p2 + 1};
            else if (sum < target) p1++;
            else p2--;
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] arr = {5, 25, 75};
        System.out.println(Arrays.toString(twoSum2(arr, 100)));
    }
}
