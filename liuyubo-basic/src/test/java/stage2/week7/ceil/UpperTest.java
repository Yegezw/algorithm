package stage2.week7.ceil;

public class UpperTest {

    private static void test() {
        Integer[] arr = {1, 1, 3, 3, 5, 5};
        for (int i = 0; i <= 6; i++) {
            System.out.println("大于 " + i + " 的最小值的索引为 " + Upper.upper(arr, i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        test();
    }

}
