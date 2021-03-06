package other.helper;

import stage1.week2.InsertionSort;
import stage1.week2.SelectionSort;
import stage2.week5.MergeSort;
import stage2.week5.MergeSortBU;
import stage2.week6.quicksort.QuickSort1;
import stage2.week6.quicksort.QuickSort2;
import stage2.week6.quicksort.QuickSort3;
import stage2.week6.work.QuickSortMid;

@SuppressWarnings("all")
public class SortingHelper {

    private SortingHelper() {
    }

    /**
     * 执行排序
     */
    private static <E extends Comparable<E>> void executeSort(SortName sortName, E[] arr) {
        switch (sortName) {
            case SelectionSort -> SelectionSort.sort(arr);
            case InsertionSort -> InsertionSort.sort(arr);

            case MergeSort -> MergeSort.sort(arr);
            case MergeSortPlus -> MergeSort.sortPlus(arr);
            case MergeSortBU -> MergeSortBU.sort(arr);

            case QuickSort1_1 -> QuickSort1.sort1(arr);
            case QuickSort1_2 -> QuickSort1.sort2(arr);
            case QuickSortMid -> QuickSortMid.sort(arr);
            case QuickSort2 -> QuickSort2.sort(arr);
            case QuickSort3 -> QuickSort3.sort(arr);
        }
    }

    /**
     * 判断当前数组是否已排序
     */
    public static <E extends Comparable<E>> boolean isSorted(E[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 排序测试
     */
    public static <E extends Comparable<E>> void sortTest(SortName sortName, E[] arr) {
        long startTime = System.nanoTime();

        executeSort(sortName, arr);

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        if (!SortingHelper.isSorted(arr)) throw new RuntimeException(sortName + " failed");
        System.out.println(String.format("%s, n = %d : %f s", sortName, arr.length, time));
    }

}
