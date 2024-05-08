package stage3.week11.unionFind;

import port.UF;

/**
 * <p>基于 rank 的优化 + 路径压缩 2
 * <p>rank[i] 代表以 i 为根的集合所表示的树的层数的排名
 * <p>让 "层数排名低的集合" 合并到 "层数排名高的集合" 上, 使得合并后的新树, 深度尽量不要增加
 * <p>平均来看, 近乎是 O(1) 级别的
 */
public class UnionFind6 implements UF
{

    private final int[] parent;
    private final int[] rank; // rank[i] 代表以 i 为根的集合所表示的树的层数的排名

    public UnionFind6(int size)
    {
        parent = new int[size];
        rank   = new int[size];
        for (int i = 0; i < size; i++)
        {
            parent[i] = i;
            rank[i]   = 1;
        }
    }

    @Override
    public int getSize()
    {
        return parent.length;
    }

    /**
     * 查找元素 p 所对应的集合编号, 同时压缩树的高度使其尽可能的矮(路径压缩)
     */
    private int find(int p)
    {
        if (p < 0 || p >= parent.length) throw new IllegalArgumentException("p is out of bound.");

        if (p == parent[p]) return p;

        parent[p] = find(parent[p]);
        return parent[p];
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

        // 让 "层数排名低的集合" 合并到 "层数排名高的集合" 上: 合并后的新树, 深度尽量不要增加
        if (rank[pRoot] < rank[qRoot]) parent[pRoot] = qRoot;
        else if (rank[qRoot] < rank[pRoot]) parent[qRoot] = pRoot;
        else
        {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }
}
