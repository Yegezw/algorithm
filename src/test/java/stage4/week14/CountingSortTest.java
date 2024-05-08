package stage4.week14;

import other.pojo.Student;

import java.util.Arrays;
import java.util.Random;

public class CountingSortTest
{

    /**
     * 验证排序结果是否正确
     */
    private static void isSorted(Student[] arr)
    {
        int n = arr.length;

        for (int i = 1; i < n; i++)
        {
            if (arr[i - 1].getScore() > arr[i].getScore())
            {
                throw new RuntimeException("Sort failed!");
            }

            if (arr[i - 1].getScore() == arr[i].getScore())
            {
                if (arr[i - 1].getName().compareTo(arr[i].getName()) > 0)
                {
                    throw new RuntimeException("Non-Stable counting sort!");
                }
            }
        }
    }

    /**
     * 测试 1, 分数 [0, 3), R = 3
     */
    public static void test1()
    {
        Student[] students = {
                new Student("A", 2), new Student("B", 1),
                new Student("C", 0), new Student("D", 1),
                new Student("E", 2), new Student("F", 0)
        };
        CountingSort.sort(students, 3);
        System.out.println(Arrays.toString(students));
    }

    /**
     * 测试 2, 分数 [0, 101),  R = 101
     */
    public static void test2()
    {
        int       n        = 26 * 26 * 26 * 26;
        Student[] students = new Student[n];
        Random    random   = new Random();

        int i = 0;
        for (char c1 = 'a'; c1 <= 'z'; c1++)
            for (char c2 = 'a'; c2 <= 'z'; c2++)
                for (char c3 = 'a'; c3 <= 'z'; c3++)
                    for (char c4 = 'a'; c4 <= 'z'; c4++)
                         students[i++] = new Student("" + c1 + c2 + c3 + c4, random.nextInt(101));

        CountingSort.sort(students, 101);
        isSorted(students);
    }

    public static void main(String[] args)
    {
        test1();

        test2();
    }
}
