package stage3.week9.work;

import org.junit.jupiter.api.Test;
import other.helper.ArrayGenerator;
import stage3.week9.queue.PriorityQueue;

import java.util.Random;

public class WorkTest
{

    boolean verifyMinHeap(MinHeap<Integer> minHeap)
    {
        int[] arr = new int[minHeap.size()];
        for (int i = 0; i < arr.length; i++) arr[i] = minHeap.extractMin();

        for (int i = 1; i < arr.length; i++)
        {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }

    @Test
    void testMinHeap()
    {
        int              n       = 1000000;
        MinHeap<Integer> minHeap = new MinHeap<>();
        Random           random  = new Random();
        for (int i = 0; i < n; i++) minHeap.add(random.nextInt(Integer.MAX_VALUE));

        if (!verifyMinHeap(minHeap)) throw new RuntimeException("Error!");
        System.out.println("Test MinHeap completed");
    }

    boolean isDescending(Integer[] arr)
    {
        for (int i = 1; i < arr.length; i++)
        {
            if (arr[i - 1] < arr[i]) return false;
        }
        return true;
    }

    @Test
    void testHeapSort1()
    {
        int       n   = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        HeapSort.sort1(arr);
        System.out.println(isDescending(arr));
    }

    @Test
    void testHeapSort2()
    {
        int       n   = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        HeapSort.sort2(arr);
        System.out.println(isDescending(arr));
    }

    @Test
    void testPQ()
    {
        Random                 random = new Random();
        PriorityQueue<Integer> queue  = new PriorityQueue<>();
        for (int i = 0; i < 10; i++) queue.enqueue(random.nextInt(10));
        for (int i = 0; i < 10; i++) System.out.println(queue.dequeue());
    }
}
