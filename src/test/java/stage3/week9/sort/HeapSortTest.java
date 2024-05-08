package stage3.week9.sort;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

public class HeapSortTest
{

    public static void testHeapSort1()
    {
        int       n   = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.HeapSort1, arr);
    }

    public static void testHeapSort2()
    {
        int       n   = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.HeapSort2, arr);
    }

    public static void main(String[] args)
    {
        testHeapSort1();

        testHeapSort2();
    }
}
