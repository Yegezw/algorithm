package vs.sort;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

import java.util.Arrays;

public class QuickSortVSMergeSort {

    private static void vs() {
        int n = 1000000;

        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

        SortingHelper.sortTest(SortName.MergeSortPlus, arr1);
        SortingHelper.sortTest(SortName.QuickSort2, arr2);
    }

    public static void main(String[] args) {
        vs();
    }

}
