package stage4.week14;

import org.junit.jupiter.api.Test;
import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

public class BucketSortTest
{

    @Test
    void testSort1()
    {
        int       n   = 10000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.BucketSort1, arr);
    }

    @Test
    void testSort2()
    {
        int       n   = 10000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.BucketSort2, arr);
    }

    @Test
    void testSort3()
    {
        int       n   = 10000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.BucketSort3, arr);
    }
}
