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

    /**
     * 针对以中间点为标定点的快速排序, 生成一个长度为 length 的特殊数组, 使得快速排序退化
     */
    public static Integer[] generateSpecialArray(int length) {
        Integer[] arr = new Integer[length];
        generateSpecialArray(arr, 0, length - 1, 0);
        return arr;
    }

    /**
     * 生成一个长度为 length 的随机字符串数组, 每个字符串的长度为 W
     */
    public static String[] generateRandomSameLengthStringArray(int length, int W) {
        String[] arr = new String[length];
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // 33 ~ 126 可打印字符 https://www.ascii-code.com/
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < W; j++) sb.append((char) (random.nextInt(94) + 33));
            arr[i] = sb.toString();
            sb.delete(0, sb.length());
        }

        return arr;
    }

    /**
     * 生成一个长度为 length 的随机字符串数组, 每个字符串的长度为 [0, bound)
     */
    public static String[] generateRandomStringArray(int length, int bound) {
        String[] arr = new String[length];
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // 33 ~ 126 可打印字符 https://www.ascii-code.com/
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < random.nextInt(bound); j++) sb.append((char) (random.nextInt(94) + 33));
            arr[i] = sb.toString();
            sb.delete(0, sb.length());
        }
        return arr;
    }

    /**
     * 递归非常灵活
     */
    private static void generateSpecialArray(Integer[] arr, int l, int r, int value) {
        if (l > r) return;

        int mid = l + (r - l) / 2;
        arr[mid] = value;

        swap(arr, l, mid);
        generateSpecialArray(arr, l + 1, r, value + 1);
        swap(arr, l, mid);
    }

    private static void swap(Integer[] arr, int a, int b) {
        Integer k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }
}
