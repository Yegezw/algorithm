package problem.week7;

import java.util.Arrays;

/**
 * <p>1011 - 在 D 天内送达包裹的能力
 * <p>https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days/
 */
@SuppressWarnings("all")
public class ShipWithinDays {

    public static int shipWithinDays(int[] weights, int days) {
        int capacityMid;
        int capacityL = Arrays.stream(weights).max().getAsInt();
        int capacityR = Arrays.stream(weights).sum();

        // arr[capacityL, capacityR] 代表运载能力
        // 二分搜索 shipTime(capacity) <= days 最大的 shipTime 对应 capacity
        // 每次循环开始时: capacityL 还没看, capacityR 可能是解, 因此当 capacityL == capacityR 时, capacityR 就是解
        while (capacityL < capacityR) {
            capacityMid = capacityL + (capacityR - capacityL) / 2;
            int time = getShipTime(weights, capacityMid);
            if (time <= days) capacityR = capacityMid;
            else capacityL = capacityMid + 1;
        }

        return capacityR;
    }

    private static int getShipTime(int[] weights, int capacity) {
        int time = 0;
        int hasCapacity = 0;
        for (int weight : weights) {
            if (hasCapacity + weight <= capacity) hasCapacity += weight;
            else {
                time++;
                hasCapacity = weight;
            }
        }
        return time + 1; // 注意
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 1, 1}; // 3
        System.out.println(shipWithinDays(weights, 4));
    }

}
