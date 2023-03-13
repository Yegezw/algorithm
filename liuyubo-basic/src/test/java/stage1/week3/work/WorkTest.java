package stage1.week3.work;

@SuppressWarnings("all")
public class WorkTest {

    private static void testLoopQueue1() {
        LoopQueue1<Integer> queue = new LoopQueue1<>();
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
    }

    private static void testLoopQueue2() {
        LoopQueue2<Integer> queue = new LoopQueue2<>();
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
    }

    private static void testLoopQueue3() {
        LoopQueue3<Integer> queue = new LoopQueue3<>();
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
    }

    public static void testDeque() {
        // 在下面的双端队列的测试中, 偶数从队尾加入、奇数从队首加入
        Deque<Integer> dq = new Deque<>();
        for (int i = 0; i < 16; i++) {
            if (i % 2 == 0) dq.addLast(i);
            else dq.addFront(i);
            System.out.println(dq);
        }

        System.out.println();

        // 之后, 我们依次从队首和队尾轮流删除元素
        for (int i = 0; !dq.isEmpty(); i++) {
            if (i % 2 == 0) dq.removeFront();
            else dq.removeLast();
            System.out.println(dq);
        }
    }

    public static void main(String[] args) {
        // testLoopQueue1();

        // testLoopQueue2();

        // testLoopQueue3();

        testDeque();
    }
}
