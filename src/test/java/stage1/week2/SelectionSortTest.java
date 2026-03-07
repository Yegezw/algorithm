package stage1.week2;

import org.junit.jupiter.api.Test;
import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;
import other.pojo.Student;

import java.util.Arrays;

@SuppressWarnings("all")
public class SelectionSortTest
{

    @Test
    void test1()
    {
        Integer[] arr = {2, 9, 6, 7, 1, 5, 4, 3, 8};
        SelectionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    void test2()
    {
        Student[] students = {
                new Student("张三", 89),
                new Student("李四", 91),
                new Student("王五", 85),
                new Student("赵六", 98)
        };
        SelectionSort.sort(students);
        System.out.println(Arrays.toString(students));
    }

    @Test
    void test3()
    {
        Integer[] arr;

        for (int i = 0; i < 100; i++)
        {
            arr = ArrayGenerator.generateRandomArray(10000, 10000);
            SelectionSort.sort1(arr);
            if (!SortingHelper.isSorted(arr))
            {
                System.out.println(false);
                return;
            }
        }
        System.out.println(true);
    }

    @Test
    void test4()
    {
        int[]     dataSize = {10000, 100000};
        Integer[] arr;

        for (int n : dataSize)
        {
            arr = ArrayGenerator.generateRandomArray(n, n);
            SortingHelper.sortTest(SortName.SelectionSort, arr);
        }
    }
}
