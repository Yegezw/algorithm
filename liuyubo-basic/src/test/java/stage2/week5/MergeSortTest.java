package stage2.week5;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

import java.util.Arrays;

@SuppressWarnings("all")
public class MergeSortTest {

    private static void testMergeSort() {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.MergeSort, arr);
    }

    private static void testMergeSortPlus() {
        int n = 100000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.MergeSortPlus, arr);
    }

    private static void testMergeSortBU() {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.MergeSortBU, arr);
    }

    private static void vs() {
        int[] dataSize = {500000, 5000000};

        for (int n : dataSize) {
            Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
            Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
            Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);

            SortingHelper.sortTest(SortName.MergeSort, arr1);
            SortingHelper.sortTest(SortName.MergeSortPlus, arr2);
            SortingHelper.sortTest(SortName.MergeSortBU, arr3);

            System.out.println("------------------------------------");
        }
    }

    public static void main(String[] args) {
        // testMergeSort();

        // testMergeSortPlus();

        // testMergeSortBU();
        
        vs();
    }
}
