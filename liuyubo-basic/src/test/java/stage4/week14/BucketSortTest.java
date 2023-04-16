package stage4.week14;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

public class BucketSortTest {

    private static void testSort1() {
        int n = 10000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.BucketSort1, arr);
    }

    private static void testSort2() {
        int n = 10000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.BucketSort2, arr);
    }

    private static void testSort3() {
        int n = 10000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.BucketSort3, arr);
    }

    public static void main(String[] args) {
        testSort1();

        testSort2();

        testSort3();
    }
}
