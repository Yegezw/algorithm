package practice3_hashmap;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/happy-number/">202 - 快乐数</a>
 */
public class Solution3 {

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();

        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getNextNumber(n);
        }

        return n == 1;
    }

    private int getNextNumber(int n) {
        int sum = 0;
        while (n != 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }
}
