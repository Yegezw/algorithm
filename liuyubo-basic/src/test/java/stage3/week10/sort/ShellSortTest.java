package stage3.week10.sort;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

import java.util.Arrays;

public class ShellSortTest {

    private static void test() {
        int n = 1000000;

        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);

        SortingHelper.sortTest(SortName.ShellSort1, arr1);
        SortingHelper.sortTest(SortName.ShellSort2, arr2);
        SortingHelper.sortTest(SortName.ShellSort3, arr3);
    }

    public static void main(String[] args) {
        test();
    }
}
