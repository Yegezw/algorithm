package other.helper;

import java.util.Random;

/**
 * 数组生成器
 */
public class ArrayGenerator {
    
    private ArrayGenerator() {
    }
    
    private static final Random RANDOM = new Random();

    /**
     * 生成一个长度为 length 的有序数组
     */
    public static Integer[] generateOrderedArray(int length) {
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++) {
            arr[i] = i;
        }
        return arr;
    }

    /**
     * 生成一个长度为 length 的随机数组, 每个数字的范围为 [0, bound]
     */
    public static Integer[] generateRandomArray(int length, int bound) {
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++) {
            arr[i] = RANDOM.nextInt(bound + 1);
        }
        return arr;
    }
}
