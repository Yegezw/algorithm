package other.helper;

import stage1.week2.InsertionSort;
import stage1.week2.SelectionSort;
import stage2.week5.MergeSort;
import stage2.week5.MergeSortBU;
import stage2.week5.MergeSortPlus;
import stage2.week6.quicksort.QuickSort1;
import stage2.week6.quicksort.QuickSort2;
import stage2.week6.quicksort.QuickSort3;
import stage2.week6.work.QuickSortMid;
import stage2.week7.work.PatienceSort;
import stage3.week10.sort.BubbleSort;
import stage3.week10.sort.ShellSort;
import stage3.week9.sort.HeapSort;
import stage4.week14.BucketSort;
import stage4.week14.LSDSort;
import stage4.week14.MSDSort;

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
            case MergeSortPlus -> MergeSortPlus.sort(arr);
            case MergeSortBU -> MergeSortBU.sort(arr);

            case QuickSort1 -> QuickSort1.sort(arr);
            case QuickSort2 -> QuickSort2.sort(arr);
            case QuickSort3 -> QuickSort3.sort(arr);
            case QuickSortMid -> QuickSortMid.sort(arr);

            case HeapSort1 -> HeapSort.sort1(arr);
            case HeapSort2 -> HeapSort.sort2(arr);

            case BubbleSort1 -> BubbleSort.sort1(arr);
            case BubbleSort2 -> BubbleSort.sort2(arr);
            case BubbleSort3 -> BubbleSort.sort3(arr);

            case ShellSort1 -> ShellSort.sort1(arr);
            case ShellSort2 -> ShellSort.sort2(arr);
            case ShellSort3 -> ShellSort.sort3(arr);

            case LSDSort -> {
                String[] strArr = (String[]) arr;
                if (strArr.length == 0) throw new IllegalArgumentException("Arr can not be empty!");
                LSDSort.sort(strArr, strArr[0].length());
            }
            case MSDSort -> MSDSort.sort((String[]) arr);

            case BucketSort1 -> BucketSort.sort1((Integer[]) arr, 200);
            case BucketSort2 -> BucketSort.sort2((Integer[]) arr, 100);
            case BucketSort3 -> BucketSort.sort3((Integer[]) arr, 10);

            case PatienceSort -> PatienceSort.sort(arr);
        }
    }

    /**
     * 判断当前数组是否已排序
     */
    public static <E extends Comparable<E>> boolean isSorted(E[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) return false;
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
