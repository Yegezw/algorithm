package stage2.week7.floor;

public class LowerTest {

    private static void test() {
        Integer[] arr = {1, 1, 3, 3, 5, 5};
        for (int i = 0; i <= 6; i++) {
            System.out.println("小于 " + i + " 的最大值的索引为 " + Lower.lower(arr, i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        test();
    }

}
