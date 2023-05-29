package chapter10.section1;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/assign-cookies/">455 - 分发饼干</a>
 */
public class FindContentChildren {

    /**
     * g[] 为每个小朋友的贪心指数, s[] 为每个饼干的大小
     */
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(s); // 饼干
        Arrays.sort(g); // 小朋友

        int res = 0;
        int si = s.length - 1; // 最大的饼干
        int gi = g.length - 1; // 最贪心的小朋友
        while (gi >= 0 && si >= 0) {
            if (s[si] >= g[gi]) {
                res++;
                si--;
                gi--;
            } else gi--;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] g1 = {1, 2, 3};
        int[] s1 = {1, 1};
        System.out.println(findContentChildren(g1, s1)); // 1

        int[] g2 = {1, 2};
        int[] s2 = {1, 2, 3};
        System.out.println(findContentChildren(g2, s2)); // 2
    }
}
