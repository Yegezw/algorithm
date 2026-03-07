package stage1.week4.recursion;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

@SuppressWarnings("all")
public class RecursionTest
{

    @Test
    void testSum()
    {
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Recursion.sum(arr));
    }

    @Test
    void testN()
    {
        int n = 5;
        System.out.println("5! = " + Recursion.factorial(n));
    }
}
