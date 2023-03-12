package stage1.week2;

/**
 * <p>插入排序法 O(n^2)
 * <p>对近乎有序的数据排序会很快 O(n)
 */
public class InsertionSort {

    private InsertionSort() {
    }

    private static <E extends Comparable<E>> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    /**
     * 正着排
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 循环不变量: arr[0, i) 已局部排序
            // 循环体维持循环不变量: 将 arr[i] 插入合适的位置(也可以理解为调整 arr[0...i] 中与 i 绑定的逆序数对)
            E k = arr[i];
            int j;
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
            // 循环不变量: arr(i, n] 已局部排序
            // 循环体维持循环不变量: 将 arr[i] 插入合适的位置(也可以理解为调整 arr[i...n] 中与 i 绑定的逆序数对)
            E k = arr[i];
            int j;
            for (j = i; j + 1 < arr.length && k.compareTo(arr[j + 1]) > 0; j++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = k;
        }
    }

    /**
     * 倒着排, 基于交换
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        for (int i = arr.length - 2; i >= 0; i--) {
            // 循环不变量: arr(i, n] 已局部排序
            // 循环体维持循环不变量: 将 arr[i] 插入合适的位置(也可以理解为调整 arr[i...n] 中与 i 绑定的逆序数对)
            for (int j = i; j + 1 < arr.length && arr[j].compareTo(arr[j + 1]) > 0; j++) {
                swap(arr, j, j + 1);
            }
        }
    }

    /**
     * 正着排, 基于交换
     */
    public static <E extends Comparable<E>> void sort1(E[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 循环不变量: arr[0, i) 已局部排序
            // 循环体维持循环不变量: 将 arr[i] 插入合适的位置(也可以理解为调整 arr[0...i] 中与 i 绑定的逆序数对)
            for (int j = i; j - 1 >= 0 && arr[j - 1].compareTo(arr[j]) > 0; j--) {
                swap(arr, j, j - 1);
            }
        }
    }
}
