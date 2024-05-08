package stage3.week9.heap;

import java.util.Arrays;
import java.util.Random;

@SuppressWarnings("all")
public class MaxHeapTest
{

    private static boolean verify(MaxHeap<Integer> maxHeap)
    {
        int[] arr = new int[maxHeap.size()];
        for (int i = 0; i < arr.length; i++) arr[i] = maxHeap.extractMax();

        for (int i = 1; i < arr.length; i++)
        {
            if (arr[i - 1] < arr[i]) return false;
        }
        return true;
    }

    private static void testHeap(Integer[] arr, boolean isHeapify)
    {
        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify) maxHeap = new MaxHeap<>(arr); // 自底向上建堆 O(n)
        else
        {
            maxHeap = new MaxHeap<>();
            for (Integer i : arr) maxHeap.add(i);    // 自顶向下建堆 O(N * logN)
        }

        long   endTime = System.nanoTime();
        double time    = (endTime - startTime) / 1000000000.0;
        if (!verify(maxHeap)) throw new RuntimeException("Error!");
        System.out.println("time: " + time + " s");
    }

    public static void test1()
    {
        int              n       = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random           random  = new Random();
        for (int i = 0; i < n; i++) maxHeap.add(random.nextInt(Integer.MAX_VALUE));

        if (!verify(maxHeap)) throw new RuntimeException("Error!");
        System.out.println("Test MaxHeap completed");
    }

    /**
     * <p>比较两种不同的建堆方式</p>
     * <p>方式一: 对每个元素进行 siftUp</p>
     * <p>方式二: 从最后一个非叶子节点开始, 倒着进行 siftDown</p>
     */
    public static void test2()
    {
        int    n      = 1000000;
        Random random = new Random();

        Integer[] arr1 = new Integer[n];
        for (int i = 0; i < n; i++) arr1[i] = (random.nextInt(Integer.MAX_VALUE));
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

        testHeap(arr1, false); // O(N * logN)
        testHeap(arr2, true);  // O(n)
    }

    public static void main(String[] args)
    {
        test1();

        test2();
    }
}
