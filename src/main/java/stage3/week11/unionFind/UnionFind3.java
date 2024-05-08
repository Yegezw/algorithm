package stage3.week11.unionFind;

import port.UF;

/**
 * <p>基于 size 的优化
 * <p>sz[i] 代表以 i 为根的集合中的元素个数
 * <p>让 "元素个数少的集合" 合并到 "元素个数多的集合" 上, 使得合并后的新树, 深度尽量不要增加
 */
public class UnionFind3 implements UF
{

    private final int[] parent;
    private final int[] sz; // sz[i] 代表以 i 为根的集合中的元素个数

    public UnionFind3(int size)
    {
        parent = new int[size];
        sz     = new int[size];
        for (int i = 0; i < size; i++)
        {
            parent[i] = i;
            sz[i]     = 1;
        }
    }

    @Override
    public int getSize()
    {
        return parent.length;
    }

    /**
     * 查找元素 p 所对应的集合编号
     */
    private int find(int p)
    {
        if (p < 0 || p >= parent.length) throw new IllegalArgumentException("p is out of bound.");
        while (p != parent[p]) p = parent[p];
        return p;
    }

    @Override
    public boolean isConnected(int p, int q)
    {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q)
    {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        // 让 "元素个数少的集合" 合并到 "元素个数多的集合" 上: 合并后的新树, 深度尽量不要增加
        if (sz[pRoot] < sz[qRoot])
        {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        else
        {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}
