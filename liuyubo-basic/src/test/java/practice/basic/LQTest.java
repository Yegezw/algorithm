package practice.basic;

public class LQTest {

    private static void testLQ1() {
        LQ1<Integer> queue = new LQ1<>();
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

    private static void testLQ2() {
        LQ2<Integer> queue = new LQ2<>();
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

    private static void testLQ3() {
        LQ3<Integer> queue = new LQ3<>();
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

    public static void main(String[] args) {
        //testLQ1();

        testLQ2();

        //testLQ3();

    }
}
