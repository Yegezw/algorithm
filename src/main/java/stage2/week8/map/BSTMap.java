package stage2.week8.map;

import port.Map;

/**
 * key 不能重复, 且必须可比较
 */
@SuppressWarnings("all")
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V>
{

    private class Node
    {
        public K    key;
        public V    value;
        public Node left;
        public Node right;

        public Node(K key, V value)
        {
            this.key   = key;
            this.value = value;
            this.left  = null;
            this.right = null;
        }
    }

    private Node root;
    private int  size;

    public BSTMap()
    {
        root = null;
        size = 0;
    }

    /**
     * 返回以 node 为根节点的二分搜索树中, 键为 key 所在的节点
     */
    private Node getNode(Node node, K key)
    {
        if (node == null) return null;

        if (key.compareTo(node.key) == 0) return node;
        if (key.compareTo(node.key) < 0) return getNode(node.left, key);
        return getNode(node.right, key);
    }

    @Override
    public void add(K key, V value)
    {
        root = add(root, key, value);
    }

    /**
     * 向以 node 为根节点的二分搜索树中添加元素 (key, value), 并返回新的根节点
     */
    private Node add(Node node, K key, V value)
    {
        if (node == null)
        {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0) node.right = add(node.right, key, value);
        else node.value = value;

        return node;
    }

    /**
     * 返回以 node 为根节点的二分搜索树中的最小元素所在的节点
     */
    private Node minimum(Node node)
    {
        if (node.left == null) return node;
        return minimum(node.left);
    }

    /**
     * 删除以 node 为根节点的二分搜索树的最小元素所在的节点, 并返回新的根节点
     */
    private Node removeMin(Node node)
    {
        if (node.left == null)
        {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public V remove(K key)
    {
        Node node = getNode(root, key);
        if (node != null)
        {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    /**
     * 以 node 为根节点的二分搜索树, 删除键为 key 所在的节点, 并返回新的根节点
     */
    private Node remove(Node node, K key)
    {
        if (node == null) return null;

        if (key.compareTo(node.key) < 0)
        {
            node.left = remove(node.left, key);
            return node;
        }
        else if (key.compareTo(node.key) > 0)
        {
            node.right = remove(node.right, key);
            return node;
        }
        else
        {
            if (node.left == null)
            {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            else if (node.right == null)
            {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            else
            {
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left  = node.left;
                node.left       = node.right = null;
                return successor;
            }
        }
    }

    @Override
    public boolean contains(K key)
    {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key)
    {
        Node node = getNode(root, key);
        return node != null ? node.value : null;
    }

    @Override
    public void set(K key, V newValue)
    {
        Node node = getNode(root, key);
        if (node != null) node.value = newValue;
        else throw new IllegalArgumentException(key + " doesn't exist!");
    }

    @Override
    public int getSize()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }
}
