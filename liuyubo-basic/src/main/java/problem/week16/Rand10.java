package problem.week16;

import java.util.Random;

/**
 * <a href="https://leetcode.cn/problems/implement-rand10-using-rand7/">470 - 用 Rand7() 实现 Rand10()</a>
 */
public class Rand10 {

    /**
     * 1 ~ 7
     */
    public int rand7() {
        return new Random().nextInt(7) + 1;
    }

    /**
     * 1 ~ 10
     */
    public int rand10() {
        while (true) {
            int a = rand7() - 1; // a in 0 ~ 6
            int b = rand7() - 1; // b in 0 ~ 6
            int t = a * 7 + b;   // t in 0 ~ 48
            if (t >= 40) continue;
            return t % 10 + 1;
        }
    }
}
