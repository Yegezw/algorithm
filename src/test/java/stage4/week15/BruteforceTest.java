package stage4.week15;

import org.junit.jupiter.api.Test;
import other.helper.MatchName;
import other.helper.SubStringMatchHelper;
import other.util.Novel;

public class BruteforceTest
{

    @Test
    void test1()
    {
        String s = "hello, this is liuyubobobo.";
        String t = "bo";
        SubStringMatchHelper.matchTest(MatchName.Bruteforce, s, t);

        System.out.println();
    }

    @Test
    void test2()
    {
        String s = Novel.words1String; // 傲慢与偏见
        String t = "china";
        SubStringMatchHelper.matchTest(MatchName.Bruteforce, s, t);
        SubStringMatchHelper.matchTest(MatchName.Bruteforce, s, "zyx");

        System.out.println();
    }

    /**
     * 暴力搜索最坏的情况
     */
    @Test
    void testWorstCase()
    {
        int n = 1000000;
        int m = 10000;

        String        s; // aaa...aaa
        String        t; // aaa...b
        StringBuilder sb = new StringBuilder();

        sb.append("a".repeat(n));
        s = sb.toString();

        sb.delete(0, sb.length());

        sb.append("a".repeat(m - 1)).append('b');
        t = sb.toString();

        SubStringMatchHelper.matchTest(MatchName.Bruteforce, s, t);
    }
}
