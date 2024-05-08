package stage2.week6.quicksort;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

public class QuickSort1Test
{

    /**
     * 测试随机数组
     */
    private static void testRandomArray()
    {
        System.out.println("RandomArray");
        int       n   = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.QuickSort1, arr);
    }

    /**
     * 测试有序数组
     */
    private static void testOrderedArray()
    {
        System.out.println("OrderedArray");
        int       n   = 1000000;
        Integer[] arr = ArrayGenerator.generateOrderedArray(n);
        SortingHelper.sortTest(SortName.QuickSort1, arr);
    }

    /**
     * 测试元素相等的数组
     */
    private static void testEqualsArray()
    {
        System.out.println("EqualsArray");
        int       n   = 100000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, 0);
        SortingHelper.sortTest(SortName.QuickSort1, arr);
    }

    public static void main(String[] args)
    {
        testRandomArray();

        testOrderedArray();

        testEqualsArray();
    }
}
