package vs.queue;

import port.Queue;
import stage1.week3.queue.ArrayQueue;
import stage1.week3.queue.LoopQueue;
import stage1.week4.queue.LinkedListQueue;

import java.util.Random;

public class ArrayQueueVSLoopQueueVSLinkedListQueue
{

    /**
     * 测试使用 q 来运行 opCount 个 enqueue 和 dequeue 所需要的时间, 单位: s
     */
    private static double testQueue(Queue<Integer> q, int opCount)
    {
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
        {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++)
        {
            q.dequeue();
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args)
    {
        int opCount = 100000;
        // int opCount = 10000000; // 请注释 ArrayQueue, 它真的太慢了

        // dequeue O(n^2)
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double              time2      = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time2 + " s");

        // dequeue O(n)
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double             time1     = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue, time: " + time1 + " s");

        // dequeue O(n)
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double                   time3           = testQueue(linkedListQueue, opCount);
        System.out.println("LinkedListQueue, time: " + time3 + " s");

        System.out.println("在 opCount 少时, LoopQueue 扩容频繁, 因此 LinkedListQueue 更占优势");
        System.out.println("在 opCount 多时, LoopQueue 扩容不再频繁, 而 LinkedListQueue 却要不停的 new, 因此 LoopQueue 更占优势");
    }
}
