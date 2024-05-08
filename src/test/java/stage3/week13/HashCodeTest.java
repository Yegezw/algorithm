package stage3.week13;

import other.pojo.Member;

import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("all")
public class HashCodeTest
{

    public static void test()
    {
        int a = 42;
        System.out.println(((Integer) a).hashCode());

        int b = -42;
        System.out.println(((Integer) b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double) c).hashCode());

        String d = "imooc";
        System.out.println(d.hashCode());

        Member member = new Member(3, 2, "bobo", "Liu");
        System.out.println(member.hashCode());
    }

    public static void testHashSet()
    {
        Member member = new Member(3, 2, "bobo", "Liu");

        HashSet<Member> set = new HashSet<>();
        set.add(member);
    }

    public static void testHashMap()
    {
        Member stu = new Member(3, 2, "bobo", "Liu");

        HashMap<Member, Integer> scores = new HashMap<>();
        scores.put(stu, 100);
    }

    public static void main(String[] args)
    {
        test();

        testHashSet();

        testHashMap();
    }
}
