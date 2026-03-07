package stage4.week14;

import org.junit.jupiter.api.Test;

public class LSDSortTest
{

    @Test
    void test()
    {
        String[] arr = {"BCA", "CAB", "ACB", "BAC", "ABC", "CBA"};
        LSDSort.sort(arr, 3);
        for (String s : arr) System.out.println(s);
    }
}
