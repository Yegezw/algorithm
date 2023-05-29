package chapter9.section2;

/**
 * <a href="https://leetcode.cn/problems/climbing-stairs/">70 - 爬楼梯</a>
 */
public class ClimbStairs {

    public static int climbStairs1(int n) {
        int[] memo = new int[n + 1]; // memo[n] = climbStairs(n)
        memo[0] = 1;  // 0 -> 1
        memo[1] = 1;  // 1 -> 1

        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }

        return memo[n];
    }

    /**
     * 降低空间复杂度
     */
    public static int climbStairs2(int n) {
        int[] memo = new int[2];
        memo[0] = 1;  // 0 -> 1
        memo[1] = 1;  // 1 -> 1

        int res = 1;
        for (int i = 2; i <= n; i++) {
            res = memo[0] + memo[1];
            memo[0] = memo[1];
            memo[1] = res;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs1(4));
        System.out.println(climbStairs2(4));
    }
}
