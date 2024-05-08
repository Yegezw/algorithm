package stage1.week3.queue;

public class LoopQueueTest
{

    private static void test()
    {
        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 10; i++)
        {
            System.out.print("i = " + i + "  ");
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2)
            {
                queue.dequeue();
                System.out.println("delete " + queue);
                System.out.println("---------------");
            }
        }
    }

    public static void main(String[] args)
    {
        test();
    }
}
