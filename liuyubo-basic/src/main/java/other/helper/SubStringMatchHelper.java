package other.helper;

import stage4.week15.KMP;
import stage4.week15.RabinKarp;
import stage4.week15.Bruteforce;

@SuppressWarnings("all")
public class SubStringMatchHelper {

    private SubStringMatchHelper() {
    }

    /**
     * 执行字符串匹配
     */
    private static int executeMatch(MatchName matchName, String s, String t) {
        return switch (matchName) {
            case Bruteforce -> Bruteforce.bruteforce(s, t);
            case RabinKarp -> RabinKarp.rabinKarp(s, t);
            case KMP -> KMP.kmp(s, t);
        };
    }

    /**
     * 执行字符串测试
     */
    public static void matchTest(MatchName matchName, String s, String t) {
        long startTime = System.nanoTime();

        int pos = executeMatch(matchName, s, t);

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        if (s.indexOf(t) != pos) throw new RuntimeException(matchName + " failed!");
        System.out.println(String.format("%s, res = %d, time = %f s", matchName, pos, time));
    }

}