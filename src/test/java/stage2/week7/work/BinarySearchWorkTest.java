package stage2.week7.work;

import org.junit.jupiter.api.Test;

public class BinarySearchWorkTest
{

    @Test
    void test()
    {
        Integer[] arr = {1, 3, 5, 7, 9};
        System.out.println(BinarySearchWork.search(arr, 7));
        System.out.println(BinarySearchWork.search(arr, 10));
    }
}
