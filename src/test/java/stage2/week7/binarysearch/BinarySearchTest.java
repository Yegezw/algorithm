package stage2.week7.binarysearch;

import org.junit.jupiter.api.Test;

public class BinarySearchTest
{

    @Test
    void testSearchR()
    {
        Integer[] arr = {1, 3, 5, 7, 9};
        int       res = BinarySearch.searchR(arr, 9);
        System.out.println(res);
    }

    @Test
    void testSearch()
    {
        Integer[] arr = {1, 3, 5, 7, 9};
        int       res = BinarySearch.search(arr, 9);
        System.out.println(res);
    }
}
