package stage1.week2;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;
import other.pojo.Student;

import java.util.Arrays;

@SuppressWarnings("all")
public class SelectionSortTest
{

    private static void test1()
    {
        Integer[] arr = {2, 9, 6, 7, 1, 5, 4, 3, 8};
        SelectionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void test2()
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

    private static void test3()
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

    private static void test4()
    {
        int[]     dataSize = {10000, 100000};
        Integer[] arr;

        for (int n : dataSize)
        {
            arr = ArrayGenerator.generateRandomArray(n, n);
            SortingHelper.sortTest(SortName.SelectionSort, arr);
        }
    }

    public static void main(String[] args)
    {
        // test1();

        // test2();

        // test3();

        test4();
    }
}
