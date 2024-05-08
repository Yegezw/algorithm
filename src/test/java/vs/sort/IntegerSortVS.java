package vs.sort;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

import java.util.Arrays;

public class IntegerSortVS
{

    private static void vs()
    {
        int n = 10000000;

        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr4 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr5 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr6 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr7 = Arrays.copyOf(arr1, arr1.length);

        SortingHelper.sortTest(SortName.MergeSortPlus, arr1);
        SortingHelper.sortTest(SortName.QuickSort2, arr2);
        SortingHelper.sortTest(SortName.HeapSort2, arr3);
        SortingHelper.sortTest(SortName.ShellSort3, arr4);
        SortingHelper.sortTest(SortName.BucketSort1, arr5);
        SortingHelper.sortTest(SortName.BucketSort2, arr6);
        SortingHelper.sortTest(SortName.BucketSort3, arr7);
    }

    public static void main(String[] args)
    {
        vs();
    }
}
