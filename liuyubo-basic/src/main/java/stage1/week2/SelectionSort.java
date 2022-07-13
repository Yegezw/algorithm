package stage1.week2;

/**
 * 选择排序法 O(n^2)
 */
@SuppressWarnings("all")
public class SelectionSort {

    private SelectionSort() {
    }

    private static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    /**
     * 正着排
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // arr[0, i) 已排序, arr[i, n) 未排序
            // 选择 arr[i, n) 最小值得索引 minIndex, 让 arr[minIndex] 与 arr[i] 做交换
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * 倒着排
     */
    public static <E extends Comparable<E>> void sort1(E[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            // arr[0, i] 未排序, arr(i, n) 已排序
            // 选择 arr[0, i] 最大值的索引 maxIndex, 让 arr[maxIndex] 与 arr[i] 做交换
            int maxIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j].compareTo(arr[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            swap(arr, i, maxIndex);
        }
    }

}
