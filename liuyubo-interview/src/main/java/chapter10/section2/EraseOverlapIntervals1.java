package chapter10.section2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <a href="https://leetcode.cn/problems/non-overlapping-intervals/">435 - 无重叠区间</a>
 */
public class EraseOverlapIntervals1 {

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });

        // memo[i] = 用 intervals[0 ... i] 的区间, 能构成的 "最长不重叠区间序列" 的长度
        int[] memo = new int[intervals.length];
        Arrays.fill(memo, 1);

        for (int i = 1; i < intervals.length; i++) {
            // 求解 memo[i]
            for (int j = 0; j <= i - 1; j++) {
                if (intervals[i][0] >= intervals[j][1]) memo[i] = Math.max(memo[i], 1 + memo[j]);
            }
        }

        int max = Arrays.stream(memo).max().getAsInt();
        return intervals.length - max;
    }

    public static void main(String[] args) {
        int[][] interval1 = {
                {1, 2},
                {2, 3},
                {3, 4},
                {1, 3}
        };
        System.out.println(eraseOverlapIntervals(interval1)); // 1

        int[][] interval2 = {
                {1, 2},
                {1, 2},
                {1, 2}
        };
        System.out.println(eraseOverlapIntervals(interval2)); // 2

        int[][] interval3 = {
                {1, 2},
                {2, 3}
        };
        System.out.println(eraseOverlapIntervals(interval3)); // 0
    }
}
