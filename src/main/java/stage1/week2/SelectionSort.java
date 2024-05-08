package stage1.week2;

/**
 * 选择排序法 O(n^2)
 */
@SuppressWarnings("all")
public class SelectionSort
{

    private SelectionSort()
    {
    }

    private static <E> void swap(E[] arr, int a, int b)
    {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    /**
     * 正着排
     */
    public static <E extends Comparable<E>> void sort(E[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++)
        {
            // 循环不变量: arr[0, i) 已排序
            // 循环体维持循环不变量: 向 arr[i] 放置剩余元素中最小的元素
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++)
            {
                if (arr[j].compareTo(arr[minIndex]) < 0) minIndex = j;
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * 倒着排
     */
    public static <E extends Comparable<E>> void sort1(E[] arr)
    {
        for (int i = arr.length - 1; i > 0; i--)
        {
            // 循环不变量: arr(i, n] 已排序
            // 循环体维持循环不变量: 向 arr[i] 放置剩余元素中最大的元素
            int maxIndex = i;
            for (int j = i - 1; j >= 0; j--)
            {
                if (arr[j].compareTo(arr[maxIndex]) > 0) maxIndex = j;
            }
            swap(arr, i, maxIndex);
        }
    }
}
