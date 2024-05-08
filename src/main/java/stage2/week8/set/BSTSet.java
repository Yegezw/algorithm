package stage2.week8.set;

import port.Set;
import stage2.week8.bst.BST;

/**
 * 基于二分搜索树 BST 实现的 Set, 存储的元素必须可比较
 */
public class BSTSet<E extends Comparable<E>> implements Set<E>
{

    private final BST<E> bst;

    public BSTSet()
    {
        bst = new BST<>();
    }

    @Override
    public void add(E e)
    {
        bst.add(e);
    }

    @Override
    public void remove(E e)
    {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e)
    {
        return bst.contains(e);
    }

    @Override
    public int getSize()
    {
        return bst.size();
    }

    @Override
    public boolean isEmpty()
    {
        return bst.isEmpty();
    }
}
