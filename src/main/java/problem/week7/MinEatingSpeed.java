package problem.week7;

import java.util.Arrays;

/**
 * <a href="https://leetcode-cn.com/problems/koko-eating-bananas/">875 - 爱吃香蕉的珂珂</a>
 */
@SuppressWarnings("all")
public class MinEatingSpeed
{

    /**
     * <p>piles = [3, 6, 7, 11], H = 8
     * <p>输出: 4
     */
    public static int minEatingSpeed(int[] piles, int h)
    {
        int speedL = 1;
        int speedR = Arrays.stream(piles).max().getAsInt();
        int speedMid;

        // arr[speedL, speedR] 代表吃香蕉的速度, time = eatingTime(speed)
        // 二分搜索 time <= h 最大的 time 对应 speed
        // 每次循环开始时: speedL 还没看, speedR 可能是解, 因此当 speedL == speedR 时, speedR 就是解
        while (speedL < speedR)
        {
            speedMid = speedL + (speedR - speedL) / 2;
            int time = getEatingTime(piles, speedMid);
            if (time <= h) speedR = speedMid;
            else speedL = speedMid + 1;
        }

        return speedR;
    }

    private static int getEatingTime(int[] piles, int speed)
    {
        int times = 0;
        for (int pile : piles)
        {
            times += pile / speed + (pile % speed == 0 ? 0 : 1);
        }
        return times;
    }

    public static void main(String[] args)
    {
        int[] piles1 = {3, 6, 7, 11}; // 4
        System.out.println(minEatingSpeed(piles1, 8));

        int[] piles2 = {30, 11, 23, 4, 20}; // 30
        System.out.println(minEatingSpeed(piles2, 5));
    }
}