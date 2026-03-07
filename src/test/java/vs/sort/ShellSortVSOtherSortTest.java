package vs.sort;

import org.junit.jupiter.api.Test;
import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

import java.util.Arrays;

public class ShellSortVSOtherSortTest
{

    @Test
    void vs()
    {
        int n = 1000000;

        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr4 = Arrays.copyOf(arr1, arr1.length);

        SortingHelper.sortTest(SortName.MergeSortPlus, arr1);
        SortingHelper.sortTest(SortName.QuickSort2, arr2);
        SortingHelper.sortTest(SortName.HeapSort2, arr3);
        SortingHelper.sortTest(SortName.ShellSort3, arr4);
    }
}
