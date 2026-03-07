package stage4.week14;

import org.junit.jupiter.api.Test;

@SuppressWarnings("all")
public class MSDSortTest
{

    @Test
    void test()
    {
        String[] arr = {"BCA", "CBAA", "AC", "BADFE", "ABC", "CBA"};
        MSDSort.sort(arr);
        for (String s : arr) System.out.println(s);
    }
}
