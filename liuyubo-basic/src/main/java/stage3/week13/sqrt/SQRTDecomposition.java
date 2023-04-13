package stage3.week13.sqrt;

import port.Merger;

import java.util.Arrays;

/**
 * <p>通用 SQRT 分解
 * <p>注意 merge 的元素可能为 null
 * <p>注意并不是每一组的元素个数都为 B(更新操作)
 * <p>N / B, 第几组
 * <p>N % B, 第几组的第几个
 */
@SuppressWarnings("all")
public class SQRTDecomposition<E> {

    private E[] data;
    private E[] blocks;
    private int N;  // 元素总数
    private int B;  // 每组元素个数
    private int Bn; // 组数
    private Merger<E> merger;

    public SQRTDecomposition(E[] arr, Merger<E> merger) {
        if (arr.length == 0) return;
        this.merger = merger;

        N = arr.length;
        B = (int) Math.sqrt(N);
        Bn = N / B + (N % B != 0 ? 1 : 0);

        data = Arrays.copyOf(arr, N);
        blocks = (E[]) new Object[Bn];
        buildBlocks();
    }

    private void buildBlocks() {
        for (int i = 0; i < N; i++) {
            if (i % B == 0) blocks[i / B] = data[i];
            else blocks[i / B] = merger.merge(blocks[i / B], data[i]);
        }
    }

    public void update(int index, E val) {
        if (index < 0 || index >= N) return;
        data[index] = val;
        
        int b = index / B;
        blocks[b] = data[b * B];
        for (int i = b * B + 1; i < Math.min((b + 1) * B, N); i++) blocks[b] = merger.merge(blocks[b], data[i]);
    }

    public E query(int l, int r) {
        if (l < 0 || l >= N || r < 0 || r >= N || l > r) return null;

        int bStart = l / B;
        int bEnd = r / B;

        E res = data[l];
        if (Math.abs(bStart - bEnd) <= 1) {
            for (int i = l + 1; i <= r; i++) res = merger.merge(res, data[i]);
        } else {
            for (int i = l + 1; i < (bStart + 1) * B; i++) res = merger.merge(res, data[i]); // bStart 组
            for (int i = bStart + 1; i < bEnd; i++) res = merger.merge(res, blocks[i]);      // [bStart + 1 ... bEnd - 1] 组
            for (int i = bEnd * B; i <= r; i++) res = merger.merge(res, data[i]);            // bEnd 组
        }

        return res;
    }
}
