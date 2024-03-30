package stage4.week14;

import stage1.week2.InsertionSort;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 桶排序
 */
@SuppressWarnings("all")
public class BucketSort {

    private BucketSort() {
    }

    public static void sort1(Integer[] arr, int B) {
        if (B <= 1) throw new IllegalArgumentException("B must be > 1");
        sort1(arr, 0, arr.length - 1, B, new Integer[arr.length]);
    }

    /**
     * 基于 MSD 思路
     */
    private static void sort1(Integer[] arr, int left, int right, int B, Integer[] temp) {
        if (left >= right) return;

        int minV = Integer.MAX_VALUE;
        int maxV = Integer.MIN_VALUE;
        for (int i = left; i <= right; i++) {
            minV = Math.min(minV, arr[i]);
            maxV = Math.max(maxV, arr[i]);
        }
        if (minV == maxV) return;
        // d 代表每个桶中的元素可能数
        int d = (maxV - minV + 1) / B + ((maxV - minV + 1) % B != 0 ? 1 : 0);

        int[] cnt = new int[B];
        int[] index = new int[B + 1];

        for (int i = left; i <= right; i++) cnt[(arr[i] - minV) / d]++;
        for (int i = 0; i < B; i++) index[i + 1] = index[i] + cnt[i];
        for (int i = left; i <= right; i++) {
            int p = (arr[i] - minV) / d;
            temp[left + index[p]] = arr[i];
            index[p] += 1;
        }
        System.arraycopy(temp, left, arr, left, right - left + 1);

        // index[0, B] 的最后一个区间是无效的
        // 需要遍历 index[0, B - 1] 中所有的区间
        sort1(arr, left, left + index[0] - 1, B, temp);
        for (int i = 0; i + 1 <= B - 1; i++) {
            sort1(arr, left + index[i], left + index[i + 1] - 1, B, temp);
        }
    }

    public static void sort2(Integer[] arr, int c) {
        // c 代表每个桶中的元素可能数
        if (c <= 0) throw new IllegalArgumentException("c must be > 0");

        int minV = Integer.MAX_VALUE;
        int maxV = Integer.MIN_VALUE;
        for (int num : arr) {
            minV = Math.min(minV, num);
            maxV = Math.max(maxV, num);
        }
        if (minV == maxV) return;

        int range = maxV - minV + 1;
        int B = range / c + (range % c != 0 ? 1 : 0);
        LinkedList<Integer>[] buckets = new LinkedList[B];
        for (int i = 0; i < B; i++) buckets[i] = new LinkedList<>();

        for (Integer num : arr) buckets[(num - minV) / c].add(num);
        for (int i = 0; i < B; i++) Collections.sort(buckets[i]);
        int index = 0;
        for (int i = 0; i < B; i++) {
            for (int num : buckets[i]) arr[index++] = num;
        }
    }

    public static void sort3(Integer[] arr, int c) {
        // c 代表每个桶中的元素可能数
        if (c <= 0) throw new IllegalArgumentException("c must be > 0");

        int minV = Integer.MAX_VALUE;
        int maxV = Integer.MIN_VALUE;
        for (Integer num : arr) {
            minV = Math.min(minV, num);
            maxV = Math.max(maxV, num);
        }
        if (minV == maxV) return;

        int range = maxV - minV + 1;                  // arr 中的元素可能数
        int B = range / c + (range % c != 0 ? 1 : 0); // 根据元素可能数决定桶的个数
        Integer[][] buckets = new Integer[B][c];      // B 个桶, 每个桶的大小姑且为 c
        int[] size = new int[B];                      // size[i] 代表 buckets[i] 中的元素数量

        for (Integer num : arr) {
            int p = (num - minV) / c;
            Integer[] bucket = buckets[p];
            if (size[p] == bucket.length) {
                resize(buckets, p);
                bucket = buckets[p];
            }
            bucket[size[p]++] = num;
        }
        for (int i = 0; i < B; i++) InsertionSort.sort(buckets[i], 0, size[i] - 1);

        int index = 0;
        for (int i = 0; i < B; i++) {
            Integer[] bucket = buckets[i];
            for (int j = 0; j < size[i]; j++) arr[index++] = bucket[j];
        }
    }

    private static void resize(Integer[][] buckets, int p) {
        Integer[] bucket = buckets[p];
        Integer[] newBucket = new Integer[(int) (bucket.length * 1.5)];
        System.arraycopy(bucket, 0, newBucket, 0, bucket.length);
        buckets[p] = newBucket;
    }
}
