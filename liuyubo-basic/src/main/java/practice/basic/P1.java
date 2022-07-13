package practice.basic;

/**
 * 练习
 * <p>线性查找法
 * <p>选择排序法
 * <p>插入排序法
 */
@SuppressWarnings("all")
public class P1 {

    private static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    /**
     * 线性查找法
     */
    public static <E> int linerSearch(E[] arr, E target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(target)) return i;
        }
        return -1;
    }

    /**
     * 选择排序法, 正着排
     */
    public static <E extends Comparable<E>> void selectionSort1(E[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) minIndex = j;
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * 选择排序法, 倒着排
     */
    public static <E extends Comparable<E>> void selectionSort2(E[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int maxIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j].compareTo(arr[maxIndex]) > 0) maxIndex = j;
            }
            swap(arr, i, maxIndex);
        }
    }

    /**
     * 插入排序法, 正着排
     */
    public static <E extends Comparable<E>> void insertionSort1(E[] arr) {
        for (int i = 1; i < arr.length; i++) {
            E k = arr[i];
            int j;
            for (j = i; j - 1 >= 0 && arr[j - 1].compareTo(k) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = k;
        }
    }

    /**
     * 插入排序法, 倒着排
     */
    public static <E extends Comparable<E>> void insertionSort2(E[] arr) {
        for (int i = arr.length - 2; i >= 0; i--) {
            E k = arr[i];
            int j;
            for (j = i; j + 1 < arr.length && k.compareTo(arr[j + 1]) > 0; j++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = k;
        }
    }

}
