package stage3.week9.sort;

import org.junit.jupiter.api.Test;
import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

public class HeapSortTest
{

    @Test
    void testHeapSort1()
    {
        int       n   = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.HeapSort1, arr);
    }

    @Test
    void testHeapSort2()
    {
        int       n   = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.HeapSort2, arr);
    }
}
