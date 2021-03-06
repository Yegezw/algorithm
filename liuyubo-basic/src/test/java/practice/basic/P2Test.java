package practice.basic;

import other.pojo.Student;

@SuppressWarnings("all")
public class P2Test {

    private static void test1() {
        P2<Integer> arr = new P2<>(20);
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        System.out.println(arr.remove(2));
        System.out.println(arr.removeFirst());
        System.out.println(arr.removeLast());
        arr.removeElement(3);
        System.out.println(arr);
    }

    private static void test2() {
        P2<Student> arr = new P2<>();

        arr.addLast(new Student("张三", 89));
        arr.addLast(new Student("李四", 91));
        arr.addLast(new Student("王五", 85));
        arr.addLast(new Student("赵六", 98));

        System.out.println(arr);
    }

    private static void test3() {
        P2<Integer> arr = new P2<>(20);
        for (int i = 0; i < 10; i++) arr.addLast(i);
        System.out.println(arr);

        arr.add(1, 100);
        arr.addFirst(100);
        System.out.println(arr);

        for (int i = 0; i < 7; i++) arr.removeFirst();
        System.out.println(arr);
    }

    public static void main(String[] args) {
        test1();

        test2();

        test3();
    }
}
