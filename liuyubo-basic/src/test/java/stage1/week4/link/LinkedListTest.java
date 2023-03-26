package stage1.week4.link;

public class LinkedListTest {

    public static void test() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 7; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        linkedList.addLast(666);
        System.out.println(linkedList);

        linkedList.removeElement(666);
        System.out.println(linkedList);

        linkedList.remove(1);
        linkedList.removeFirst();
        linkedList.removeLast();
        System.out.println(linkedList);

        System.out.println(linkedList.getSize());
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());
    }

    public static void main(String[] args) {
        test();
    }
}
