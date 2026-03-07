package stage1.week3.stack;

import org.junit.jupiter.api.Test;

@SuppressWarnings("all")
public class ArrayStackTest
{

    @Test
    void test()
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
}
