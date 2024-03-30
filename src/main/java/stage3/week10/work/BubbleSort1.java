package stage3.week10.work;

/**
 * <p>正着冒泡
 * <p>优化: 对完全有序的数组 O(n)
 * <p>每一轮都会减少逆序对, 当逆序对数量减为 0 时, 就有序了
 */
public class BubbleSort1 {

    private BubbleSort1() {
    }

    /**
     * 冒泡排序
     */
    public static <E extends Comparable<E>> void sort1(E[] arr) {
        for (int tail = arr.length - 1; tail >= 1; tail--) {
            // 通过冒泡在 arr[tail] 放上合适的元素
            for (int i = 0; i + 1 <= tail; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) swap(arr, i, i + 1);
            }
        }
    }

    /**
     * 冒泡排序优化 1
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        for (int tail = arr.length - 1; tail >= 1; tail--) {
            // 通过冒泡在 arr[tail] 放上合适的元素
            boolean isSwapped = false;
            for (int i = 0; i + 1 <= tail; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    isSwapped = true;
                }
            }

            if (!isSwapped) return;
        }
    }

    /**
     * 冒泡排序优化 2
     */
    public static <E extends Comparable<E>> void sort3(E[] arr) {
        for (int tail = arr.length - 1; tail >= 1; ) {
            // 通过冒泡在 arr[tail] 放上合适的元素
            int nextTail = 0; // 如果一次都没 swap, 就应该退出
            for (int i = 0; i + 1 <= tail; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    nextTail = i;
                }
            }

            tail = nextTail;
        }
    }

    private static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }
}
