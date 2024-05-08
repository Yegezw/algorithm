package stage1.week4.stack;

public class LinkedListStackTest
{

    public static void test()
    {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        for (int i = 0; i < 5; i++)
        {
            stack.push(i);
            System.out.println(stack);
        }

        System.out.println(stack.pop());
        System.out.println(stack);

        System.out.println("size = " + stack.getSize());
    }

    public static void main(String[] args)
    {
        test();
    }
}
