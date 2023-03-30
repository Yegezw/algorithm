package stage2.week7.work;

public class BinarySearchWorkTest {

    private static void test() {
        Integer[] arr = {1, 3, 5, 7, 9};
        System.out.println(BinarySearchWork.search(arr, 7));
        System.out.println(BinarySearchWork.search(arr, 10));
    }

    public static void main(String[] args) {
        test();
    }
}
