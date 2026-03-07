package stage3.week9.queue;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class PriorityQueueTest
{

    @Test
    void test()
    {
        Random                 random = new Random();
        PriorityQueue<Integer> queue  = new PriorityQueue<>();
        for (int i = 0; i < 10; i++) queue.enqueue(random.nextInt(10));
        for (int i = 0; i < 10; i++) System.out.println(queue.dequeue());
    }
}
