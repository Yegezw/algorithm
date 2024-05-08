package problem.week6;

import java.util.Arrays;
import java.util.Random;

/**
 * <a href="https://leetcode.cn/problems/smallest-k-lcci/">程序员面试金典 17.14. 最小 K 个数</a>
 * <p>求数组升序排序好后, 从左往右数共 K 个元素
 * <p>复杂度: O(n)
 */
@SuppressWarnings("all")
public class SmallestK
{

    public static int[] smallestK(int[] arr, int k)
    {
        if (k == 0) return new int[0];
        return selectK(arr, 0, arr.length - 1, k - 1, new Random());
    }

    private static int[] selectK(int[] arr, int l, int r, int k, Random random)
    {
        int p = partition(arr, l, r, random);

        if (p == k) return Arrays.copyOf(arr, k + 1);

        if (p < k) return selectK(arr, p + 1, r, k, random);
        return selectK(arr, l, p - 1, k, random);
    }

    /**
     * 双路快速排序
     */
    private static int partition(int[] arr, int l, int r, Random random)
    {
        int p = random.nextInt(r - l + 1) + l;
        swap(arr, l, p);

        int v  = arr[l];
        int p1 = l + 1;
        int p2 = r;

        while (true)
        {
            while (p1 <= p2 && arr[p1] < v) p1++;
            while (p1 <= p2 && arr[p2] > v) p2--;

            if (p1 >= p2) break;

            swap(arr, p1++, p2--);
        }

        swap(arr, l, p2);
        return p2;
    }

    private static void swap(int[] arr, int a, int b)
    {
        int k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    public static void main(String[] args)
    {
        int[] arr = {1, 8, 3, 4, 6, 2, 5, 9, 7};
        System.out.println(Arrays.toString(smallestK(arr, 5))); // [1, 2, 3, 4, 5]
    }
}
