package problem.week7;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days/">1011 - 在 D 天内送达包裹的能力</a>
 */
@SuppressWarnings("all")
public class ShipWithinDays {

    public static int shipWithinDays(int[] weights, int days) {
        int capacityL = Arrays.stream(weights).max().getAsInt();
        int capacityR = Arrays.stream(weights).sum();
        int capacityMid;

        // arr[capacityL, capacityR] 代表运载能力, time = getShipTime(capacity)
        // 二分搜索 time <= days 最大的 time 对应 capacity
        // 每次循环开始时: capacityL 还没看, capacityR 可能是解, 因此当 capacityL == capacityR 时, capacityR 就是解
        while (capacityL < capacityR) {
            capacityMid = capacityL + (capacityR - capacityL) / 2;
            int times = getShipTime(weights, capacityMid);
            if (times <= days) capacityR = capacityMid;
            else capacityL = capacityMid + 1;
        }

        return capacityR;
    }

    private static int getShipTime(int[] weights, int maxCapacity) {
        int times = 0;
        int hasCapacity = 0;
        for (int weight : weights) {
            if (hasCapacity + weight <= maxCapacity) hasCapacity += weight;
            else {
                times++;
                hasCapacity = weight;
            }
        }
        return times + 1;
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // 15
        System.out.println(shipWithinDays(weights, 5));
    }
}
