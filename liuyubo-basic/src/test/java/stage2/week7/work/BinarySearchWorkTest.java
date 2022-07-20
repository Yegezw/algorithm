package stage2.week7.work;

public class BinarySearchWorkTest {

    private static void test() {
        Integer[] arr = {1, 3, 5, 7, 9};
        int res = BinarySearchWork.search(arr, 9);
        System.out.println(res);
    }

    public static void main(String[] args) {
        test();
    }

}
