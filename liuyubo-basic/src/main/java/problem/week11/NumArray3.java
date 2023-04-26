package problem.week11;

import java.util.Arrays;

/**
 * <a href="https://leetcode-cn.com/problems/range-sum-query-mutable/">307 - 区域和检索 - 数组可修改</a>
 * <p>通过线段树解决
 */
@SuppressWarnings("all")
public class NumArray3 {

    private final int[] tree;
    private final int[] data;

    public NumArray3(int[] nums) {
        data = Arrays.copyOf(nums, nums.length);
        tree = new int[nums.length * 4];
        buildSegmentTree(0, 0, data.length - 1);
    }

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

        tree[treeIndex] = tree[leftTreeIndex] + tree[rightTreeIndex];
    }

    public void update(int index, int val) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is illegal.");
        }
        if (data[index] == val) return;

        data[index] = val;
        update(0, 0, data.length - 1, index, val);
    }

    private void update(int treeIndex, int l, int r, int index, int val) {
        if (l == r) {
            tree[treeIndex] = val;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index <= mid) update(leftTreeIndex, l, mid, index, val);
        else update(rightTreeIndex, mid + 1, r, index, val);

        tree[treeIndex] = tree[leftTreeIndex] + tree[rightTreeIndex];
    }

    public int sumRange(int left, int right) {
        // [0, data.length - 1], left <= right
        if (left < 0 || left >= data.length || right < 0 || right >= data.length || left > right) {
            throw new IllegalArgumentException("index is illegal.");
        }
        return query(0, 0, data.length - 1, left, right);
    }

    private int query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryR <= mid) return query(leftTreeIndex, l, mid, queryL, queryR);
        else if (queryL >= mid + 1) return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        else {
            int leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            int rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            return leftResult + rightResult;
        }
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray3 x = new NumArray3(nums);

        System.out.println(x.sumRange(0, 2)); // 1

        x.update(1, 2);

        System.out.println(x.sumRange(0, 2)); // 3
        System.out.println(x.sumRange(3, 5)); // -4
        System.out.println(x.sumRange(0, 5)); // -1
    }
}
