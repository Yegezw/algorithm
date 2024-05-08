package stage4.week14;

import other.pojo.Student;

/**
 * 计数排序是稳定的 O(N + R)
 */
public class CountingSort
{

    private CountingSort()
    {
    }

    /**
     * 计数排序 O(N + R)
     */
    public static void sort(Student[] students, int R)
    {
        // 分数的取值范围是 [0, R)
        int[] cnt = new int[R];
        for (Student student : students) cnt[student.getScore()]++;

        // [index[i], index[i + 1]) 区间的值为 i
        int[] index = new int[R + 1];
        for (int i = 0; i < R; i++) index[i + 1] = index[i] + cnt[i];

        Student[] temp = new Student[students.length];
        for (Student student : students)
        {
            int score = student.getScore();
            temp[index[score]] = student;
            index[score] += 1;
        }

        System.arraycopy(temp, 0, students, 0, temp.length);
    }
}
