package stage1.week2;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

@SuppressWarnings("all")
public class InsertSortTest {

    private static void test() {
        int[] dataSize = {10000, 100000};
        Integer[] arr;

        for (int n : dataSize) {
            arr = ArrayGenerator.generateRandomArray(n, n);
            SortingHelper.sortTest(SortName.InsertionSort, arr);
        }
    }

    public static void main(String[] args) {
        test();
    }
}
