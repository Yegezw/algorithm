package stage3.week10.sort;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

import java.util.Arrays;

public class BubbleSortTest {

    private static void test() {
        int n = 100000;

        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        System.out.println("Random Array");
        SortingHelper.sortTest(SortName.BubbleSort1, arr1);
        SortingHelper.sortTest(SortName.BubbleSort2, arr2);
        SortingHelper.sortTest(SortName.BubbleSort3, arr3);

        System.out.println("Order Array");
        SortingHelper.sortTest(SortName.BubbleSort1, arr1);
        SortingHelper.sortTest(SortName.BubbleSort2, arr2);
        SortingHelper.sortTest(SortName.BubbleSort3, arr3);
    }

    public static void main(String[] args) {
        test();
    }
}
