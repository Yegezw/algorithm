package practice.basic;

public class DQTest {

    private static void testDQ() {
        DQ<Integer> dq = new DQ<>();
        for (int i = 0; i < 16; i++) {
            if (i % 2 == 0) dq.addLast(i);
            else dq.addFront(i);
            System.out.println(dq);
        }

        System.out.println();

        for (int i = 0; !dq.isEmpty(); i++) {
            if (i % 2 == 0) dq.removeFront();
            else dq.removeLast();
            System.out.println(dq);
        }
    }

    public static void main(String[] args) {
        testDQ();
    }
}
