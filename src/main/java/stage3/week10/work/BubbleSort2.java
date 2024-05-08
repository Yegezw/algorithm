package stage3.week10.work;

/**
 * <p>倒着冒泡
 * <p>优化: 对完全有序的数组 O(n)
 * <p>每一轮都会减少逆序对, 当逆序对数量减为 0 时, 就有序了
 */
public class BubbleSort2
{

    private BubbleSort2()
    {
    }

    /**
     * 冒泡排序
     */
    public static <E extends Comparable<E>> void sort1(E[] arr)
    {
        for (int front = 0; front < arr.length - 1; front++)
        {
            // 通过冒泡在 arr[front] 放上合适的元素
            for (int i = arr.length - 1; i - 1 >= front; i--)
            {
                if (arr[i].compareTo(arr[i - 1]) < 0) swap(arr, i, i - 1);
            }
        }
    }

    /**
     * 冒泡排序优化 1
     */
    public static <E extends Comparable<E>> void sort2(E[] arr)
    {
        for (int front = 0; front < arr.length - 1; front++)
        {
            // 通过冒泡在 arr[front] 放上合适的元素
            boolean isSwapped = false;
            for (int i = arr.length - 1; i - 1 >= front; i--)
            {
                if (arr[i].compareTo(arr[i - 1]) < 0)
                {
                    swap(arr, i, i - 1);
                    isSwapped = true;
                }
            }

            if (!isSwapped) return;
        }
    }

    /**
     * 冒泡排序优化 2
     */
    public static <E extends Comparable<E>> void sort3(E[] arr)
    {
        for (int front = 0; front < arr.length - 1; )
        {
            // 通过冒泡在 arr[front] 放上合适的元素
            int nextFront = arr.length - 1; // 如果一次都没 swap, 就应该退出
            for (int i = arr.length - 1; i - 1 >= front; i--)
            {
                if (arr[i].compareTo(arr[i - 1]) < 0)
                {
                    swap(arr, i, i - 1);
                    nextFront = i;
                }
            }

            front = nextFront;
        }
    }

    private static <E> void swap(E[] arr, int a, int b)
    {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }
}
