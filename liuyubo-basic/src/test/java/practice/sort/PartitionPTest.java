package practice.sort;

import other.helper.ArrayGenerator;
import other.helper.SortingHelper;

import java.util.Arrays;

@SuppressWarnings("all")
public class PartitionPTest {

    /**
     * 单路快速排序
     */
    private static <E extends Comparable<E>> void sort1(E[] arr) {
        process1(arr, 0, arr.length - 1);
        System.out.println(SortingHelper.isSorted(arr));
    }

    private static <E extends Comparable<E>> void process1(E[] arr, int l, int r) {
        if (r - l <= 7) {
            PartitionP.insertionSort(arr, l, r);
            return;
        }

        int p = PartitionP.partition1(arr, l, r);

        process1(arr, l, p - 1);
        process1(arr, p + 1, r);
    }

    /**
     * 双路快速排序
     */
    private static <E extends Comparable<E>> void sort2(E[] arr) {
        process2(arr, 0, arr.length - 1);
        System.out.println(SortingHelper.isSorted(arr));
    }

    private static <E extends Comparable<E>> void process2(E[] arr, int l, int r) {
        if (r - l <= 7) {
            PartitionP.insertionSort(arr, l, r);
            return;
        }

        int p = PartitionP.partition2(arr, l, r);

        process2(arr, l, p - 1);
        process2(arr, p + 1, r);
    }

    /**
     * 三路快速排序
     */
    private static <E extends Comparable<E>> void sort3(E[] arr) {
        process3(arr, 0, arr.length - 1);
        System.out.println(SortingHelper.isSorted(arr));
    }

    private static <E extends Comparable<E>> void process3(E[] arr, int l, int r) {
        if (r - l <= 7) {
            PartitionP.insertionSort(arr, l, r);
            return;
        }

        int p[] = PartitionP.partition3(arr, l, r);

        process1(arr, l, p[0]);
        process1(arr, p[1], r);
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr1, arr2, arr3;

        System.out.println("RandomArray");
        arr1 = ArrayGenerator.generateRandomArray(n, n);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);
        sort1(arr1);
        sort2(arr2);
        sort3(arr3);

        System.out.println("OrderedArray");
        arr1 = ArrayGenerator.generateOrderedArray(n);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);
        sort1(arr1);
        sort2(arr2);
        sort3(arr3);

        System.out.println("EqualsArray");
        arr1 = ArrayGenerator.generateRandomArray(n, 0);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);
        sort1(arr1);
        sort2(arr2);
        sort3(arr3);
    }

}
