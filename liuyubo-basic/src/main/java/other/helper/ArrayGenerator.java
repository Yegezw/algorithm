package other.helper;

/**
 * 数组生成器
 */
public class ArrayGenerator {
    
    private ArrayGenerator() {
    }

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
}
