package stage1.week4.queue;

public class LinkedListQueueTest {

    public static void test() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            System.out.print("i = " + i + "  ");
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println("delete " + queue);
                System.out.println("---------------");
            }
        }
        
        System.out.println("size = " + queue.getSize());
    }

    public static void main(String[] args) {
        test();
    }
}
