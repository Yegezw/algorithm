package stage2.week7.work;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

import java.util.Arrays;

public class PatienceSortTest
{

    /**
     * 测试随机数组
     */
    private static void testRandomArray()
    {
        System.out.println("RandomArray");
        int n = 1000000;

        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

        SortingHelper.sortTest(SortName.PatienceSort, arr1);
        SortingHelper.sortTest(SortName.MergeSortPlus, arr2);
    }

    public static void main(String[] args)
    {
        testRandomArray();
    }
}
