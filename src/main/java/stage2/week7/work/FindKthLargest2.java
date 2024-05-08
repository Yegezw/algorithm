package stage2.week7.work;

import java.util.Random;

/**
 * <a href="https://leetcode-cn.com/problems/kth-largest-element-in-an-array/">215 - 数组中的第 K 个最大元素</a>
 * <p>求数组升序排序好后, 从右往左数第 K 个元素, 它的索引是 length - K
 * <p>复杂度: O(n)
 */
@SuppressWarnings("all")
public class FindKthLargest2
{

    /**
     * 非递归: 在 arr[l, r) 中查找第 K 大的元素
     */
    public static int findKthLargest(int[] arr, int k)
    {
        int target = arr.length - k;

        int    l      = 0;
        int    r      = arr.length;
        int    p;
        Random random = new Random();

        // 在 arr[l, r) 中查找 target 位置的元素
        while (l < r)
        {
            p = partition(arr, l, r, random);
            if (p == target) return arr[p];
            if (p < target) l = p + 1;
            else r = p;
        }

        throw new RuntimeException("No Solution");
    }

    /**
     * 在 arr[l, r) 进行 partition
     */
    private static int partition(int[] arr, int l, int r, Random random)
    {
        int p = random.nextInt(r - l) + l;
        swap(arr, l, p);

        int v  = arr[l];
        int p1 = l + 1;
        int p2 = r - 1;

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
}
