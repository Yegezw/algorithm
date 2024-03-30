package vs.sort;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

import java.util.Arrays;

public class SelectionSortVSInsertionSort {

    public static void test() {
        int[] dataSize = {10000, 100000};

        Integer[] arr1;
        Integer[] arr2;
        for (int n : dataSize) {
            System.out.println("Random Array:");
            arr1 = ArrayGenerator.generateRandomArray(n, n);
            arr2 = Arrays.copyOf(arr1, arr1.length);
            SortingHelper.sortTest(SortName.SelectionSort, arr1);
            SortingHelper.sortTest(SortName.InsertionSort, arr2);

            System.out.println("Ordered Array:");
            SortingHelper.sortTest(SortName.SelectionSort, arr1);
            SortingHelper.sortTest(SortName.InsertionSort, arr2);

            System.out.println("-------------------------------------");
        }
    }

    public static void main(String[] args) {
        test();
    }
}
