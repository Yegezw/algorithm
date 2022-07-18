package stage2.week6.quicksort;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

import java.util.Arrays;

@SuppressWarnings("all")
public class QuickSort1Test {

    private static void testQuickSort1_1() {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.QuickSort1_1, arr);
    }

    private static void testQuickSort1_2() {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.QuickSort1_2, arr);
    }

    /**
     * 测试随机数组
     */
    private static void vsRandom() {
        System.out.println("RandomArray");
        int n = 1000000;
        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

        SortingHelper.sortTest(SortName.QuickSort1_1, arr1);
        SortingHelper.sortTest(SortName.QuickSort1_2, arr2);
    }

    /**
     * 测试有序数组
     */
    private static void vsOrdered() {
        System.out.println("OrderedArray");
        int n = 1000000;
        Integer[] arr1 = ArrayGenerator.generateOrderedArray(n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

        SortingHelper.sortTest(SortName.QuickSort1_1, arr1);
        SortingHelper.sortTest(SortName.QuickSort1_2, arr2);
    }

    /**
     * 测试元素相等的数组
     */
    private static void vsEquals() {
        System.out.println("EqualsArray");
        int n = 100000;
        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, 0);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

        SortingHelper.sortTest(SortName.QuickSort1_1, arr1);
        SortingHelper.sortTest(SortName.QuickSort1_2, arr2);
    }

    public static void main(String[] args) {
        //testQuickSort1_1();

        //testQuickSort1_2();

        //vsRandom();

        //vsOrdered();

        //vsEquals();
    }

}
