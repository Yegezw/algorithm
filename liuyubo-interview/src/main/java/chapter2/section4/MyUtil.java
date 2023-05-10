package chapter2.section4;

import java.util.Random;

public class MyUtil {

    private MyUtil() {
    }

    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        if (n <= 0 || rangeL > rangeR) throw new IllegalArgumentException();

        Integer[] arr = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) arr[i] = random.nextInt(rangeR - rangeL + 1) + rangeL;
        return arr;
    }

    public static Integer[] generateOrderedArray(int n) {
        if (n <= 0) throw new IllegalArgumentException();

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        return arr;
    }
}
