package stage1.week2;

/**
 * 插入排序法 O(n^2)
 * <p>对近乎有序的数据排序会很快 O(n)
 */
@SuppressWarnings("all")
public class InsertionSort {

    private InsertionSort() {
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
        for (int i = 1; i < arr.length; i++) {
            // 把 arr[i] 插入合适的位置
            E k = arr[i];
            int j; // j 代表 k 应该插入的位置
            for (j = i; j - 1 >= 0 && arr[j - 1].compareTo(k) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = k;
        }
    }

    /**
     * 倒着排
     */
    public static <E extends Comparable<E>> void sort3(E[] arr) {
        for (int i = arr.length - 2; i >= 0; i--) {
            // 把 arr[i] 插入合适的位置
            E k = arr[i];
            int j; // j 代表 k 应该插入的位置
            for (j = i; j + 1 < arr.length && k.compareTo(arr[j + 1]) > 0; j++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = k;
        }
    }

    /**
     * 交换 2
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // arr[0, i) 已排序, arr[i, n) 未排序
            // 把 arr[i] 插入合适的位置(理解为调整 arr[0...i] 中的逆序数对)
            for (int j = i; j - 1 >= 0 && arr[j - 1].compareTo(arr[j]) > 0; j--) {
                swap(arr, j - 1, j);
            }
        }
    }

    /**
     * 交换 1
     */
    public static <E extends Comparable<E>> void sort1(E[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // arr[0, i) 已排序, arr[i, n) 未排序
            // 把 arr[i] 插入合适的位置(理解为调整 arr[0...i] 中的逆序数对)
            for (int j = i; j - 1 >= 0; j--) {
                if (arr[j - 1].compareTo(arr[j]) > 0) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

}
