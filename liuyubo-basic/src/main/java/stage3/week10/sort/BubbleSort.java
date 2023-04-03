package stage3.week10.sort;

/**
 * <p>冒泡排序法: O(n^2)
 * <p>优化: 对完全有序的数组 O(n)
 * <p>每一轮都会减少逆序对, 当逆序对数量减为 0 时, 就有序了
 */
@SuppressWarnings("all")
public class BubbleSort {

    private BubbleSort() {
    }

    /**
     * 冒泡排序
     */
    public static <E extends Comparable<E>> void sort1(E[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // arr(n - 1 - i, n - 1] 已排好序
            // 通过冒泡在 arr[n - 1 - i] 位置放上合适的元素
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) swap(arr, j, j + 1);
            }
        }
    }

    /**
     * 冒泡排序优化 1
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // arr(n - 1 - i, n - 1] 已排好序
            // 通过冒泡在 arr[n - 1 - i] 位置放上合适的元素
            boolean isSwapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    isSwapped = true;
                }
            }

            if (!isSwapped) break;
        }
    }

    /**
     * 冒泡排序优化 2
     */
    public static <E extends Comparable<E>> void sort3(E[] arr) {
        for (int i = 0; i < arr.length - 1; ) {
            // arr(n - 1 - i, n - 1] 已排好序
            // 通过冒泡在 arr[n - 1 - i] 位置放上合适的元素
            int lastSwappedIndex = 0;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    lastSwappedIndex = j + 1;
                }
            }

            i = arr.length - lastSwappedIndex;
        }
    }

    private static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }
}
