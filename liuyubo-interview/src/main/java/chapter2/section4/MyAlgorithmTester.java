package chapter2.section4;

@SuppressWarnings("all")
public class MyAlgorithmTester {

    private MyAlgorithmTester() {
    }

    /**
     * O(logN)
     */
    public static <E extends Comparable<E>> int binarySearch(E[] arr, E target) {
        int l = 0;
        int r = arr.length - 1;
        int mid;

        while (l <= r) {
            mid = l + (r - l) / 2;
            if (target.compareTo(arr[mid]) == 0) return mid;
            if (target.compareTo(arr[mid]) > 0) l = mid + 1;
            else r = mid - 1;
        }

        return -1;
    }

    /**
     * O(N)
     */
    public static Integer findMax(Integer[] arr) {
        if (arr == null) return -1;

        Integer res = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > res) res = arr[i];
        }
        return res;
    }

    /**
     * O(N * logN)
     */
    public static <E extends Comparable<E>> void mergeSort(E[] arr) {
        int n = arr.length;
        E[] temp = (E[]) new Comparable[n];

        for (int size = 1; size < n; size += size) {
            // arr[i ... i + size - 1] 和 arr[i + size ... i + size + size - 1]
            for (int i = 0; i + size < n; i += 2 * size) {
                if (arr[i + size - 1].compareTo(arr[i + size]) > 0) {
                    merge(arr, i, i + size - 1, Math.min(i + size + size - 1, n - 1), temp);
                }
            }
        }
    }

    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp) {
        System.arraycopy(arr, l, temp, l, r - l + 1);

        int p1 = l;
        int p2 = mid + 1;
        int i = l;

        while (p1 <= mid && p2 <= r) arr[i++] = temp[p1].compareTo(temp[p2]) <= 0 ? temp[p1++] : temp[p2++];
        while (p1 <= mid) arr[i++] = temp[p1++];
        while (p2 <= r) arr[i++] = temp[p2++];
    }

    /**
     * O(N ^ 2)
     */
    public static <E extends Comparable<E>> void selectionSort(E[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) minIndex = j;
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(Object[] arr, int a, int b) {
        if (a < 0 || a >= arr.length) throw new IllegalArgumentException("a is out of bound");
        if (b < 0 || b >= arr.length) throw new IllegalArgumentException("b is out of bound");

        Object k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }
}
