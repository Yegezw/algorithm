package stage1.week3.array;

import other.pojo.Student;

import java.util.Iterator;

@SuppressWarnings("all")
public class ArrayTest
{

    private static void test1()
    {
        Array<Integer> arr = new Array<>(20);
        for (int i = 0; i < 10; i++)
        {
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

    private static void test2()
    {
        Array<Student> arr = new Array<>();

        arr.addLast(new Student("张三", 89));
        arr.addLast(new Student("李四", 91));
        arr.addLast(new Student("王五", 85));
        arr.addLast(new Student("赵六", 98));

        System.out.println(arr);
    }

    private static void test3()
    {
        Array<Integer> arr = new Array<>(20);
        for (int i = 0; i < 10; i++) arr.addLast(i);
        System.out.println(arr);

        arr.add(1, 100);
        arr.addFirst(100);
        System.out.println(arr);

        for (int i = 0; i < 7; i++) arr.removeFirst();
        System.out.println(arr);
    }

    private static void test4()
    {
        Array<Integer> arr = new Array<>(10);
        for (int i = 0; i < 10; i++) arr.addLast(i);

        Iterator<Integer> iterator = arr.iterator();
        while (iterator.hasNext())
        {
            int i = iterator.next();
            if (i % 3 == 1) iterator.remove(); // 删除 1 4 7
            else System.out.print(i + " ");
        }
    }

    public static void main(String[] args)
    {
        // test1();

        // test2();

        // test3();

        test4();
    }
}
