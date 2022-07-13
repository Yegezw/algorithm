package practice.link;

public class LinkRTest {

    public static void test() {
        LinkR<Integer> linkedListR = new LinkR<>();
        for (int i = 0; i < 7; i++) {
            linkedListR.addFirst(i);
            System.out.println(linkedListR);
        }

        linkedListR.add(2, 666);
        linkedListR.addLast(666);
        System.out.println(linkedListR);

        linkedListR.removeElement(666);
        System.out.println(linkedListR);

        linkedListR.remove(1);
        linkedListR.removeFirst();
        linkedListR.removeLast();
        System.out.println(linkedListR);

        System.out.println(linkedListR.getSize());
        System.out.println(linkedListR.getFirst());
        System.out.println(linkedListR.getLast());
    }

    public static void main(String[] args) {
        test();
    }

}
