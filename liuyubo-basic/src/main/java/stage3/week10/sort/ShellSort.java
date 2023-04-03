package stage3.week10.sort;

/**
 * <p>希尔排序
 * <p>复杂度: O(n^2 - n^2 / 2^logN), O(n^1.3) ~ O(n^1.5)
 */
public class ShellSort {

    private ShellSort() {
    }

    /**
     * 希尔排序
     */
    public static <E extends Comparable<E>> void sort1(E[] arr) {
        int h = arr.length / 2; // h = 间隔数 = 组数, 分母 = 每组的元素数
        while (h >= 1) {
            for (int start = 0; start < h; start++) {
                // 对 arr[start, start + h, start + 2h ...] 进行插入排序
                for (int i = start + h; i < arr.length; i += h) {
                    E k = arr[i];
                    int j;
                    for (j = i; j - h >= start && arr[j - h].compareTo(k) > 0; j -= h) {
                        arr[j] = arr[j - h];
                    }
                    arr[j] = k;
                }
            }
            h /= 2;
        }
    }

    /**
     * 代码优化
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        int h = arr.length / 2; // h = 间隔数 = 组数, 分母 = 每组的元素数
        while (h >= 1) {
            // 对 arr[h, n - 1] 进行插入排序
            for (int i = h; i < arr.length; i++) {
                E k = arr[i];
                int j;
                for (j = i; j - h >= 0 && arr[j - h].compareTo(k) > 0; j -= h) arr[j] = arr[j - h];
                arr[j] = k;
            }
            h /= 2;
        }
    }

    /**
     * 性能优化: 新的步长序列
     */
    public static <E extends Comparable<E>> void sort3(E[] arr) {
        int h = 1; // h = 间隔数 = 组数, 分母 = 每组的元素数
        while (h < arr.length) h = h * 3 + 1;
        // 1, 4, 13, 40 ...
        while (h >= 1) {
            // 对 arr[h, n - 1] 进行插入排序
            for (int i = h; i < arr.length; i++) {
                E k = arr[i];
                int j;
                for (j = i; j - h >= 0 && arr[j - h].compareTo(k) > 0; j -= h) arr[j] = arr[j - h];
                arr[j] = k;
            }
            h /= 3;
        }
    }
}
