package stage2.week5;

/**
 * 自底向上归并排序
 */
@SuppressWarnings("all")
public class MergeSortBU {

    private MergeSortBU() {
    }

    /**
     * 自底向上归并排序
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        int n = arr.length;
        E[] temp = (E[]) new Comparable[n];

        // 对 arr[0, n - 1] 中所有的 arr[i, i + 15], 使用插入排序法
        for (int i = 0; i < n; i += 16) {
            insertionSort(arr, i, Math.min(i + 15, n - 1));
        }

        // 遍历合并的区间长度(把 2 个区间合并成 1 个区间)
        for (int sz = 16; sz < n; sz += sz) {
            // 遍历合并的两个区间的起始位置 i
            // 合并 arr[i, i + sz - 1] 和 arr[i + sz, Math.min(i + sz + sz - 1, n - 1)]
            // 只要右区间存在, 哪怕右区间只有一个元素, 也需要归并, 因此归并条件为: i + sz < n
            for (int i = 0; i + sz < n; i += 2 * sz) {
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0) {
                    merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1), temp);
                }
            }
        }
    }

    /**
     * 合并两个有序数组 arr[l, mid]、arr[mid + 1, r], 使得 arr[l, r] 整体有序
     */
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp) {
        System.arraycopy(arr, l, temp, l, r - l + 1);

        int p1 = l;
        int p2 = mid + 1;
        int i = l;

        while (p1 <= mid && p2 <= r) arr[i++] = temp[p1].compareTo(temp[p2]) < 0 ? temp[p1++] : temp[p2++];
        while (p1 <= mid) arr[i++] = temp[p1++];
        while (p2 <= r) arr[i++] = temp[p2++];
    }

    /**
     * 对 arr[l, r] 进行插入排序
     */
    private static <E extends Comparable<E>> void insertionSort(E[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            E k = arr[i];
            int j;
            for (j = i; j - 1 >= l && arr[j - 1].compareTo(k) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = k;
        }
    }

}
