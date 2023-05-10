package chapter2.section4;

@SuppressWarnings("all")
public class Main {

    /**
     * 数据规模倍乘测试 findMax
     */
    private static void testFindMax() {
        System.out.println("Test for findMax");
        for (int i = 20; i <= 26; i++) {
            int n = (int) Math.pow(2, i);
            Integer[] arr = MyUtil.generateRandomArray(n, 0, 10000000);

            long startTime = System.currentTimeMillis();
            MyAlgorithmTester.findMax(arr);
            long endTime = System.currentTimeMillis();

            System.out.print("data size 2 ^ " + i + " = " + n + "\t");
            System.out.println("Time cost: " + (endTime - startTime) + " ms");
        }
    }

    /**
     * 数据规模倍乘测试 selectionSort
     */
    private static void testSelectionSort() {
        System.out.println("Test for Selection Sort");
        for (int i = 10; i <= 16; i++) {
            int n = (int) Math.pow(2, i);
            Integer[] arr = MyUtil.generateRandomArray(n, 0, 10000000);

            long startTime = System.currentTimeMillis();
            MyAlgorithmTester.selectionSort(arr);
            long endTime = System.currentTimeMillis();

            System.out.print("data size 2 ^ " + i + " = " + n + "\t\t");
            System.out.println("Time cost: " + (endTime - startTime) + " ms");
        }
    }

    /**
     * 数据规模倍乘测试 binarySearch
     */
    private static void testBinarySearch() {
        System.out.println("Test for Binary Search");
        for (int i = 14; i <= 26; i++) {
            int n = (int) Math.pow(2, i);
            Integer[] arr = MyUtil.generateOrderedArray(n);

            long startTime = System.nanoTime();
            MyAlgorithmTester.binarySearch(arr, 0);
            long endTime = System.nanoTime();

            System.out.print("data size 2 ^ " + i + " = " + n + "\t\t");
            System.out.println("Time cost: " + (endTime - startTime) / 1000.0 + " μs");
        }
    }

    /**
     * 数据规模倍乘测试 mergeSort
     */
    private static void testMergeSort() {
        System.out.println("Test for Merge Sort");
        for (int i = 14; i <= 25; i++) {
            int n = (int) Math.pow(2, i);
            Integer[] arr = MyUtil.generateRandomArray(n, 0, 1 << 30);

            long startTime = System.currentTimeMillis();
            MyAlgorithmTester.mergeSort(arr);
            long endTime = System.currentTimeMillis();

            System.out.print("data size 2 ^ " + i + " = " + n + "\t\t");
            System.out.println("Time cost: " + (endTime - startTime) / 1000.0 + " s");
        }
    }

    public static void main(String[] args) {
        // testFindMax();

        // testSelectionSort();

        // testBinarySearch();

        // testMergeSort();
    }
}
