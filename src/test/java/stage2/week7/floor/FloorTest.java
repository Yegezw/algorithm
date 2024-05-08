package stage2.week7.floor;

import other.helper.ArrayGenerator;

import java.util.Arrays;

public class FloorTest
{

    private static void testFloorL()
    {
        System.out.println("存在 target 时, 返回 = target 的最左边的索引");

        Integer[] arr   = {1, 1, 3, 3, 5, 5};
        Integer[] index = ArrayGenerator.generateOrderedArray(7);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(index));

        for (int i = 0; i <= 6; i++)
        {
            System.out.println("小于 " + i + " 的最大值的索引为 " + Floor.floorL(arr, i));
        }
        System.out.println();
    }

    private static void testFloorR()
    {
        System.out.println("存在 target 时, 返回 = target 的最右边的索引");

        Integer[] arr   = {1, 1, 3, 3, 5, 5};
        Integer[] index = ArrayGenerator.generateOrderedArray(7);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(index));

        for (int i = 0; i <= 6; i++)
        {
            System.out.println("小于 " + i + " 的最大值的索引为 " + Floor.floorR(arr, i));
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        testFloorL();

        testFloorR();
    }
}
