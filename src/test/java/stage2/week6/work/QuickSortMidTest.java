package stage2.week6.work;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

import java.util.Arrays;

@SuppressWarnings("all")
public class QuickSortMidTest {

    /**
     * 测试随机数组
     */
    private static void testRandomArray() {
        System.out.println("RandomArray");
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.QuickSortMid, arr);
    }

    /**
     * 测试有序数组
     */
    private static void testOrderedArray() {
        System.out.println("OrderedArray");
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateOrderedArray(n);
        SortingHelper.sortTest(SortName.QuickSortMid, arr);
    }

    /**
     * 测试元素相等的数组
     */
    private static void testEqualsArray() {
        System.out.println("EqualsArray");
        int n = 100000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, 0);
        SortingHelper.sortTest(SortName.QuickSortMid, arr);
    }

    /**
     * 测试特殊数组
     */
    private static void testSpecialArray() {
        System.out.println("SpecialArray");
        int n = 100000;
        Integer[] arr = ArrayGenerator.generateSpecialArray(n);
        SortingHelper.sortTest(SortName.QuickSortMid, arr);
    }

    /**
     * 生成特殊数组, 比如 [8, 2, 6, 4, 0, 1, 3, 5, 7, 9]
     */
    private static void generateSpecialArray() {
        Integer[] arr = ArrayGenerator.generateSpecialArray(10);
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        // testRandomArray();

        // testOrderedArray();

        // testEqualsArray();

        // testSpecialArray();

        generateSpecialArray();
    }
}
