package chapter4.section6;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/number-of-boomerangs/">447 - 回旋镖的数量</a>
 */
public class NumberOfBoomerangs {

    /**
     * (i, j, k) 使得 distance(points[i], points[j]) == distance(points[i], points[k])
     */
    public static int numberOfBoomerangs(int[][] points) {
        int res = 0;

        for (int i = 0; i < points.length; i++) {
            // Key -> distance(point[i] ,point[x]), Value -> 个数
            Map<Integer, Integer> map = new HashMap<>();

            for (int x = 0; x < points.length; x++) {
                if (x == i) continue;
                int distance = getDistance(points[i], points[x]);
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }

            for (int value : map.values()) {
                if (value >= 2) res += value * (value - 1);
            }
        }

        return res;
    }

    public static int getDistance(int[] pointA, int[] pointB) {
        int xDiff = pointA[0] - pointB[0];
        int yDiff = pointA[1] - pointB[1];
        return (xDiff * xDiff) + (yDiff * yDiff);
    }

    public static void main(String[] args) {
        int[][] points = {
                {0, 0},
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1},
        };
        System.out.println(numberOfBoomerangs(points));
    }
}
