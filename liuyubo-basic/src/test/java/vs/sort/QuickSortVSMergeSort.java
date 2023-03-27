package vs.sort;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

import java.util.Arrays;

public class QuickSortVSMergeSort {

    private static void vs() {
        int[] dataSize = {500000, 5000000};

        Integer[] arr1;
        Integer[] arr2;
        
        for (int n : dataSize) {
            arr1 = ArrayGenerator.generateRandomArray(n, n);
            arr2 = Arrays.copyOf(arr1, arr1.length);

            SortingHelper.sortTest(SortName.QuickSort2, arr1);
            SortingHelper.sortTest(SortName.MergeSortPlus, arr2);

            System.out.println("---------------------------------------");
        }
    }

    public static void main(String[] args) {
        vs();
    }
}
