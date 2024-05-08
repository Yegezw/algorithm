package stage2.week7.ceil;

import other.helper.ArrayGenerator;

import java.util.Arrays;

public class CeilTest
{

    private static void testCeilR()
    {
        System.out.println("存在 target 时, 返回 = target 的最右边的索引");

        Integer[] arr   = {1, 1, 3, 3, 5, 5};
        Integer[] index = ArrayGenerator.generateOrderedArray(7);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(index));

        for (int i = 0; i <= 6; i++)
        {
            System.out.println("大于 " + i + " 的最小值的索引为 " + Ceil.ceilR(arr, i));
        }
        System.out.println();
    }

    private static void testCeilL()
    {
        System.out.println("存在 target 时, 返回 = target 的最左边的索引");

        Integer[] arr   = {1, 1, 3, 3, 5, 5};
        Integer[] index = ArrayGenerator.generateOrderedArray(7);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(index));

        for (int i = 0; i <= 6; i++)
        {
            System.out.println("大于 " + i + " 的最小值的索引为 " + Ceil.ceilL(arr, i));
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        testCeilR();

        testCeilL();
    }
}
