package stage3.week12.map;

import port.Map;
import stage3.week12.tree.RBTree;

public class RBMap<K extends Comparable<K>, V> implements Map<K, V>
{

    private final RBTree<K, V> rbTree;

    public RBMap()
    {
        rbTree = new RBTree<>();
    }

    @Override
    public void add(K key, V value)
    {
        rbTree.add(key, value);
    }

    @Override
    public V remove(K key)
    {
        return rbTree.remove(key);
    }

    @Override
    public boolean contains(K key)
    {
        return rbTree.contains(key);
    }

    @Override
    public V get(K key)
    {
        return rbTree.get(key);
    }

    @Override
    public void set(K key, V newValue)
    {
        rbTree.set(key, newValue);
    }

    @Override
    public int getSize()
    {
        return rbTree.getSize();
    }

    @Override
    public boolean isEmpty()
    {
        return rbTree.isEmpty();
    }
}
