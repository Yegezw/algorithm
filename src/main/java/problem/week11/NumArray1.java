package problem.week11;

import java.util.Arrays;

/**
 * <a href="https://leetcode-cn.com/problems/range-sum-query-immutable/">303 - 区域和检索 - 数组不可变</a>
 * <p>通过线段树解决
 */
@SuppressWarnings("all")
public class NumArray1
{

    private final int[] data;
    private final int[] tree;

    public NumArray1(int[] nums)
    {
        data = Arrays.copyOf(nums, nums.length);
        tree = new int[nums.length * 4];
        buildSegmentTree(0, 0, nums.length - 1);
    }

    private void buildSegmentTree(int treeIndex, int l, int r)
    {
        if (l == r)
        {
            tree[treeIndex] = data[l];
            return;
        }

        int mid            = l + (r - l) / 2;
        int leftTreeIndex  = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = tree[leftTreeIndex] + tree[rightTreeIndex];
    }

    public int sumRange(int left, int right)
    {
        // [0, data.length - 1], left <= right
        if (left < 0 || left >= data.length || right < 0 || right >= data.length || left > right)
        {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return query(0, 0, data.length - 1, left, right);
    }

    private int query(int treeIndex, int l, int r, int queryL, int queryR)
    {
        if (l == queryL && r == queryR) return tree[treeIndex];

        int mid            = l + (r - l) / 2;
        int leftTreeIndex  = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid + 1) return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        else if (queryR <= mid) return query(leftTreeIndex, l, mid, queryL, queryR);
        else
        {
            int leftSum  = query(leftTreeIndex, l, mid, queryL, mid);
            int rightSum = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            return leftSum + rightSum;
        }
    }

    private int leftChild(int index)
    {
        return index * 2 + 1;
    }

    private int rightChild(int index)
    {
        return index * 2 + 2;
    }
}
