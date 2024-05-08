package stage1.week4.recursion;

/**
 * 递归
 */
@SuppressWarnings("all")
public class Recursion
{

    /**
     * n!
     */
    public static int factorial(int n)
    {
        if (n == 1) return 1;
        return n * factorial(n - 1);
    }

    /**
     * 数组求和
     */
    public static int sum(int[] arr)
    {
        return sum(arr, 0);
    }

    private static int sum(int[] arr, int l)
    {
        if (l == arr.length) return 0;
        return arr[l] + sum(arr, l + 1);
    }
}
