package practice.basic;

import other.helper.ArrayGenerator;
import other.helper.SortingHelper;
import practice.basic.P1;

import java.util.Random;

@SuppressWarnings("all")
public class P1Test {

    private static final Random random = new Random();

    private static void testLinerSearch() {
        Integer[] arr = ArrayGenerator.generateOrderedArray(10000);

        for (int i = 0; i < 100; i++) {
            Integer target = random.nextInt(10000);
            int position = P1.linerSearch(arr, target);
            if (!arr[position].equals(target)) {
                System.out.println(false);
                return;
            }
        }
        System.out.println(true);
    }

    private static void testSelectionSort1() {
        Integer[] arr;

        for (int i = 0; i < 100; i++) {
            arr = ArrayGenerator.generateRandomArray(1000, 1000);
            P1.selectionSort1(arr);
            if (!SortingHelper.isSorted(arr)) {
                System.out.println(false);
                return;
            }
        }

        System.out.println(true);
    }

    private static void testSelectionSort2() {
        Integer[] arr;

        for (int i = 0; i < 100; i++) {
            arr = ArrayGenerator.generateRandomArray(1000, 1000);
            P1.selectionSort2(arr);
            if (!SortingHelper.isSorted(arr)) {
                System.out.println(false);
                return;
            }
        }

        System.out.println(true);
    }

    private static void testinsertionSort1() {
        Integer[] arr;

        for (int i = 0; i < 100; i++) {
            arr = ArrayGenerator.generateRandomArray(1000, 1000);
            P1.insertionSort1(arr);
            if (!SortingHelper.isSorted(arr)) {
                System.out.println(false);
                return;
            }
        }

        System.out.println(true);
    }

    private static void testinsertionSort2() {
        Integer[] arr;

        for (int i = 0; i < 100; i++) {
            arr = ArrayGenerator.generateRandomArray(1000, 1000);
            P1.insertionSort2(arr);
            if (!SortingHelper.isSorted(arr)) {
                System.out.println(false);
                return;
            }
        }

        System.out.println(true);
    }

    public static void main(String[] args) {

        testLinerSearch();

        testSelectionSort1();
        testSelectionSort2();

        testinsertionSort1();
        testinsertionSort2();

    }

}
