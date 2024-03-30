package stage3.week11.segmentTree;

import port.Merger;

import java.util.Arrays;

/**
 * <p>线段树: 基于 Merger 接口的 merge(E a, E b) 方法
 * <p>tree[treeIndex] 代表 data[l ... r] 的融合结果
 */
@SuppressWarnings("all")
public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = Arrays.copyOf(arr, arr.length);
        tree = (E[]) new Object[arr.length * 4];
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 返回满二叉树的数组表示中, 一个索引所表示的元素的左孩子节点的索引
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回满二叉树的数组表示中, 一个索引所表示的元素的右孩子节点的索引
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * <p>在 tree[treeIndex] 的位置创建表示区间 data[l ... r] 的线段树
     * <p>复杂度: O(4 * n)
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 返回区间 [queryL ... queryR] 的值
     */
    public E query(int queryL, int queryR) {
        // queryL <= queryR, queryIndex in [0, data.length - 1]
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * <p>在以 treeIndex 为根的线段树中 [l ... r] 的范围里, 搜索区间 [queryL ... queryR] 的值
     * <p>复杂度: O(logN)
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) return tree[treeIndex];

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid + 1) return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        else if (queryR <= mid) return query(leftTreeIndex, l, mid, queryL, queryR);
        else {
            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            return merger.merge(leftResult, rightResult);
        }
    }

    /**
     * 基于 equals 判断是否真的进行了更新操作
     */
    public void set(int index, E value) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Set failed, require 0 <= index < length");
        }
        if (data[index].equals(value)) return;

        data[index] = value;
        set(0, 0, data.length - 1, index, value);
    }

    /**
     * <p>在以 treeIndex 为根的线段树中更新 index 的值为 value
     * <p>复杂度: O(logN)
     */
    private void set(int treeIndex, int l, int r, int index, E value) {
        if (l == r) {
            tree[treeIndex] = value;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index <= mid) set(leftTreeIndex, l, mid, index, value);
        else set(rightTreeIndex, mid + 1, r, index, value);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Get failed, require 0 <= index < length");
        }
        return data[index];
    }

    public int getSize() {
        return data.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(tree);
    }
}
