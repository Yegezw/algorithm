package stage1.week1;

import other.helper.ArrayGenerator;
import other.pojo.Teacher;

@SuppressWarnings("all")
public class LinearSearchTest {

    private static void test1() {
        Integer[] data = {6, 9, 7, 2, 5, 4};
        System.out.println(LinearSearch.search(data, 5));
        System.out.println(LinearSearch.search(data, 15));
    }

    private static void test2() {
        Teacher[] teachers = {
                new Teacher("张三"),
                new Teacher("李四"),
                new Teacher("王五")
        };
        System.out.println(LinearSearch.search(teachers, new Teacher("李四")));
        System.out.println(LinearSearch.search(teachers, new Teacher("赵六")));
    }

    private static void test3() {
        int[] dataSize = {1000000, 10000000};
        Integer[] arr;

        for (int n : dataSize) {
            arr = ArrayGenerator.generateOrderedArray(n);

            long startTime = System.nanoTime();
            for (int k = 0; k < 100; k++) {
                LinearSearch.search(arr, n);
            }
            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("n = " + n + ", 100 runs: " + time + " s");
        }
    }

    public static void main(String[] args) {
        // test1();

        // test2();

        test3();
    }
}
