package stage1.week3.stack;

@SuppressWarnings("all")
public class ArrayStackTest
{

    public static void test()
    {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 5; i++)
        {
            stack.push(i);
            System.out.println(stack);
        }

        System.out.println(stack.pop());
        System.out.println(stack);
    }

    public static void main(String[] args)
    {
        test();
    }
}
