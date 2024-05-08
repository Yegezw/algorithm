package stage2.week7.binarysearch;

public class BinarySearchTest
{

    private static void testSearchR()
    {
        Integer[] arr = {1, 3, 5, 7, 9};
        int       res = BinarySearch.searchR(arr, 9);
        System.out.println(res);
    }

    private static void testSearch()
    {
        Integer[] arr = {1, 3, 5, 7, 9};
        int       res = BinarySearch.search(arr, 9);
        System.out.println(res);
    }

    public static void main(String[] args)
    {
        testSearchR();

        testSearch();
    }
}
