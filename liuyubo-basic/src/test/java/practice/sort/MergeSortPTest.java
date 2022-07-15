package practice.sort;

import other.helper.ArrayGenerator;
import other.helper.SortingHelper;

public class MergeSortPTest {

    private static void test() {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);

        MergeSortP.sort(arr);
        System.out.println(SortingHelper.isSorted(arr));
    }

    public static void main(String[] args) {
        test();
    }

}
