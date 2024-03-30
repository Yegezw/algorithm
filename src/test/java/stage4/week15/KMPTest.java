package stage4.week15;

import other.helper.MatchName;
import other.helper.SubStringMatchHelper;
import other.util.Novel;

public class KMPTest {

    public static void test1() {
        String s = "hello, this is liuyubobobo.";
        String t = "bo";
        SubStringMatchHelper.matchTest(MatchName.KMP, s, t);

        System.out.println();
    }

    public static void test2() {
        String s = Novel.words1String; // 傲慢与偏见
        String t = "china";
        SubStringMatchHelper.matchTest(MatchName.KMP, s, t);
        SubStringMatchHelper.matchTest(MatchName.KMP, s, "zyx");

        System.out.println();
    }

    /**
     * 暴力搜索最坏的情况, 但 KMP 却可以非常快的完成
     */
    public static void testWorstCase() {
        int n = 1000000;
        int m = 10000;

        String s; // aaa...aaa
        String t; // aaa...b
        StringBuilder sb = new StringBuilder();

        sb.append("a".repeat(n));
        s = sb.toString();

        sb.delete(0, sb.length());

        sb.append("a".repeat(m - 1)).append('b');
        t = sb.toString();

        SubStringMatchHelper.matchTest(MatchName.KMP, s, t);
    }

    public static void main(String[] args) {
        test1();

        test2();

        testWorstCase();
    }
}
