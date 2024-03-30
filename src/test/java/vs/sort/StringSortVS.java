package vs.sort;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

import java.util.Arrays;

@SuppressWarnings("all")
public class StringSortVS {

    private static void quickSortVSLSDSort() {
        int n = 10000000;
        // int W = 200;
        int W = 2;

        String[] arr1 = ArrayGenerator.generateRandomSameLengthStringArray(n, W);
        String[] arr2 = Arrays.copyOf(arr1, n);
        String[] arr3 = Arrays.copyOf(arr1, n);

        SortingHelper.sortTest(SortName.QuickSort2, arr1);
        SortingHelper.sortTest(SortName.QuickSort3, arr2);
        SortingHelper.sortTest(SortName.LSDSort, arr3);
    }

    private static void quickSortVSMSDSort() {
        int n = 50000000;
        // int bound = 200;
        int bound = 20;

        String[] arr1 = ArrayGenerator.generateRandomStringArray(n, bound);
        String[] arr2 = Arrays.copyOf(arr1, n);

        SortingHelper.sortTest(SortName.QuickSort2, arr1);
        SortingHelper.sortTest(SortName.MSDSort, arr2);
    }

    public static void main(String[] args) {
        quickSortVSLSDSort();

        System.out.println();

        quickSortVSMSDSort();
    }
}
