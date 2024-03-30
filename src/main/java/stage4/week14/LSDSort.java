package stage4.week14;

import java.util.Arrays;

/**
 * <p>RadixSort
 * <p>Least Significant Digit 字符串排序算法
 * <p>String[] arr 中, 每个字符串的长度应该相等
 * <p>复杂度 O(W * (N + R)) => O(n)
 */
public class LSDSort {

    private LSDSort() {
    }

    /**
     * O(W * (N + R)) => O(n)
     */
    public static void sort(String[] arr, int W) {
        for (String s : arr) {
            if (s.length() != W) throw new IllegalArgumentException("All Strings' length must be the same.");
        }

        // 字符范围 [0, 256)
        int R = 256;
        int[] cnt = new int[R];
        int[] index = new int[R + 1];
        String[] temp = new String[arr.length];

        for (int r = W - 1; r >= 0; r--) {
            // 基于第 radix 位字符进行计数排序

            Arrays.fill(cnt, 0);
            for (String s : arr) cnt[s.charAt(r)]++;

            for (int i = 0; i < R; i++) index[i + 1] = index[i] + cnt[i];

            for (String s : arr) {
                char c = s.charAt(r);
                temp[index[c]] = s;
                index[c] += 1;
            }

            System.arraycopy(temp, 0, arr, 0, temp.length);
        }
    }
}
