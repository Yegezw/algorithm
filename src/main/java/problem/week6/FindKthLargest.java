package problem.week6;

import java.util.Random;

/**
 * <a href="https://leetcode-cn.com/problems/kth-largest-element-in-an-array/">215 - 数组中的第 K 个最大元素</a>
 * <p>求数组升序排序好后, 从右往左数第 K 个元素, 它的索引是 length - K
 * <p>复杂度: O(n)
 */
@SuppressWarnings("all")
public class FindKthLargest
{

    public static int findKthLargest(int[] arr, int k)
    {
        return selectK(arr, 0, arr.length - 1, arr.length - k, new Random());
    }

    private static int selectK(int[] arr, int l, int r, int k, Random random)
    {
        int p = partition(arr, l, r, random);

        if (p == k) return arr[p];

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
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6}; // k = 4, res = 4
        System.out.println(findKthLargest(arr, 4));
    }
}
