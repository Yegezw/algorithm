package vs.sort;

import org.junit.jupiter.api.Test;
import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

import java.util.Arrays;

public class MergeSortVSOtherSortTest
{

    @Test
    void vs()
    {
        int n = 100000;

        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);

        SortingHelper.sortTest(SortName.SelectionSort, arr1);
        SortingHelper.sortTest(SortName.InsertionSort, arr2);
        SortingHelper.sortTest(SortName.MergeSortPlus, arr3);
    }
}
