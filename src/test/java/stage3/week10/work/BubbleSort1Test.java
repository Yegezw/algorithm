package stage3.week10.work;

import org.junit.jupiter.api.Test;
import other.helper.ArrayGenerator;
import other.helper.SortingHelper;

import java.util.Arrays;

public class BubbleSort1Test
{

    @Test
    void test()
    {
        int       n    = 10000;
        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, n);
        Integer[] arr3 = Arrays.copyOf(arr1, n);

        BubbleSort1.sort1(arr1);
        BubbleSort1.sort2(arr2);
        BubbleSort1.sort3(arr3);

        System.out.println(SortingHelper.isSorted(arr1));
        System.out.println(SortingHelper.isSorted(arr2));
        System.out.println(SortingHelper.isSorted(arr3));
    }
}
