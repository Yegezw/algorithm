package stage3.week9.work;

import other.helper.ArrayGenerator;
import stage3.week9.queue.PriorityQueue;

import java.util.Random;

public class WorkTest {

    private static boolean verifyMinHeap(MinHeap<Integer> minHeap) {
        int[] arr = new int[minHeap.size()];
        for (int i = 0; i < arr.length; i++) arr[i] = minHeap.extractMin();

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }

    public static void testMinHeap() {
        int n = 1000000;
        MinHeap<Integer> minHeap = new MinHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) minHeap.add(random.nextInt(Integer.MAX_VALUE));

        if (!verifyMinHeap(minHeap)) throw new RuntimeException("Error!");
        System.out.println("Test MinHeap completed");
    }

    private static boolean isDescending(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i]) return false;
        }
        return true;
    }

    public static void testHeapSort1() {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        HeapSort.sort1(arr);
        System.out.println(isDescending(arr));
    }

    public static void testHeapSort2() {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        HeapSort.sort2(arr);
        System.out.println(isDescending(arr));
    }

    private static void testPQ() {
        Random random = new Random();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < 10; i++) queue.enqueue(random.nextInt(10));
        for (int i = 0; i < 10; i++) System.out.println(queue.dequeue());
    }

    public static void main(String[] args) {
        testMinHeap();

        testHeapSort1();

        testHeapSort2();

        testPQ();
    }
}
