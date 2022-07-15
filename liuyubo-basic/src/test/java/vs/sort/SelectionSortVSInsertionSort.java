package vs.sort;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

import java.util.Arrays;

public class SelectionSortVSInsertionSort {

    public static void test() {
        int[] dataSize = {10000, 100000};

        for (int n : dataSize) {
            System.out.println("Random Array:");
            Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
            Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
            SortingHelper.sortTest(SortName.SelectionSort, arr1);
            SortingHelper.sortTest(SortName.InsertionSort, arr2);

            System.out.println("Order Array:");
            Integer[] arr3 = ArrayGenerator.generateOrderedArray(n);
            Integer[] arr4 = Arrays.copyOf(arr3, arr3.length);
            SortingHelper.sortTest(SortName.SelectionSort, arr3);
            SortingHelper.sortTest(SortName.InsertionSort, arr4);

            System.out.println("-------------------------------------");
        }
    }

    public static void main(String[] args) {
        test();
    }

}
