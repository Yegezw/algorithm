package chapter10.section2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <a href="https://leetcode.cn/problems/non-overlapping-intervals/">435 - 无重叠区间</a>
 */
public class EraseOverlapIntervals2 {

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });

        int res = 1;
        int pre = 0; // intervals[pre] 为上一个保留的区间
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= intervals[pre][1]) {
                res++;
                pre = i;
            }
        }

        return intervals.length - res;
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
