package vs.queue;

import port.Queue;
import stage1.week3.queue.ArrayQueue;
import stage1.week3.queue.LoopQueue;

import java.util.Random;

public class ArrayQueueVSLoopQueue {

    /**
     * 测试使用 q 来运行 opCount 个 enqueue 和 dequeue 所需要的时间, 单位: s
     */
    private static double testQueue(Queue<Integer> q, int opCount) {
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 100000;
        // int opCount = 1000000;

        // dequeue O(n^2)
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time2 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time2 + " s");

        // dequeue O(n)
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time1 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue, time: " + time1 + " s");
    }
}
