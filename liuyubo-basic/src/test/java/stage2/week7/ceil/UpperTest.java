package stage2.week7.ceil;

import other.helper.ArrayGenerator;

import java.util.Arrays;

public class UpperTest {

    private static void test() {
        Integer[] arr = {1, 1, 3, 3, 5, 5};
        Integer[] index = ArrayGenerator.generateOrderedArray(7);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(index));

        for (int i = 0; i <= 6; i++) {
            System.out.println("大于 " + i + " 的最小值的索引为 " + Upper.upper(arr, i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        test();
    }
}
