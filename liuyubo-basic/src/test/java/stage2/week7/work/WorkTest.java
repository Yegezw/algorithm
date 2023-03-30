package stage2.week7.work;

import other.helper.ArrayGenerator;
import other.helper.SortingHelper;

@SuppressWarnings("all")
public class WorkTest {

    /**
     * 测试 SelectK 非递归
     */
    private static void testFindKthLargest1() {
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6}; // k = 4, res = 4
        System.out.println(FindKthLargest1.findKthLargest(arr, 4));
    }

    /**
     * 测试 SelectK 非递归变种
     */
    private static void testFindKthLargest2() {
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6}; // k = 4, res = 4
        System.out.println(FindKthLargest2.findKthLargest(arr, 4));
    }

    /**
     * 测试 MergeSort 变种
     */
    private static void testMergeSortWork() {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        MergeSortWork.sort(arr);
        System.out.println(SortingHelper.isSorted(arr));
    }

    public static void main(String[] args) {
        testFindKthLargest1();

        testFindKthLargest2();

        testMergeSortWork();
    }
}
