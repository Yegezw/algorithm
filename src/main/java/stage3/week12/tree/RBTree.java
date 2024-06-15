package stage3.week12.tree;

/**
 * <p>Red-Black-Tree 红黑树
 * <p>红黑树是保持 "黑平衡" 的二叉树
 * <p>红黑树添加和删除比 AVL 树快, 查询比 AVL 树慢(它比 AVL 树更高)
 */
@SuppressWarnings("all")
public class RBTree<K extends Comparable<K>, V>
{

    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private class Node
    {
        public K       key;
        public V       value;
        public Node    left;
        public Node    right;
        public boolean color;

        public Node(K key, V value)
        {
            this.key   = key;
            this.value = value;
            this.left  = null;
            this.right = null;
            this.color = RED; // 默认插入红色节点
        }
    }

    private Node root;
    private int  size;

    public RBTree()
    {
        root = null;
        size = 0;
    }

    /**
     * 判断节点 node 的颜色
     */
    private boolean isRed(Node node)
    {
        // 空节点是黑色的
        if (node == null) return BLACK;
        return node.color;
    }

    /**
     * 对节点 node 进行左旋转操作, 返回旋转后新的根节点 x
     */
    //   node                            x
    //  /    \     向左旋转 (node)      /    \
    // T1     x   - - - - - - - ->   node   T3
    //      /   \                   /    \
    //     T2   T3                 T1     T2
    private Node leftRotate(Node node)
    {
        Node x = node.right;

        // 向左旋转过程
        node.right = x.left;
        x.left     = node;

        // 调整颜色
        x.color    = node.color;
        node.color = RED;

        return x;
    }

    /**
     * 对节点 node 进行右旋转操作, 返回旋转后新的根节点 x
     */
    //      node                           x
    //     /    \     向右旋转 (node)     /    \
    //    x     T3   - - - - - - - ->   T1   node
    //  /   \                               /    \
    // T1    T2                            T2    T3
    private Node rightRotate(Node node)
    {
        Node x = node.left;

        // 向右旋转过程
        node.left = x.right;
        x.right   = node;

        // 调整颜色
        x.color    = node.color;
        node.color = RED;

        return x;
    }

    /**
     * 颜色翻转
     */
    private void flipColors(Node node)
    {
        node.color      = RED;
        node.left.color = node.right.color = BLACK;
    }

    /**
     * 返回以 node 为根节点的红黑树中, 键为 key 所在的节点
     */
    private Node getNode(Node node, K key)
    {
        if (node == null) return null;

        if (key.compareTo(node.key) == 0) return node;
        if (key.compareTo(node.key) < 0) return getNode(node.left, key);
        return getNode(node.right, key);
    }

    /**
     * 向红黑树中添加元素 (key, value)
     */
    public void add(K key, V value)
    {
        root       = add(root, key, value);
        root.color = BLACK; // 保持根节点为黑色节点
    }

    /**
     * 向以 node 为根节点的红黑树中添加元素 (key, value), 并返回新的根节点
     */
    private Node add(Node node, K key, V value)
    {
        if (node == null)
        {
            size++;
            return new Node(key, value); // 默认插入红色节点
        }

        if (key.compareTo(node.key) < 0) node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0) node.right = add(node.right, key, value);
        else node.value = value;

        // 保持 "黑平衡"
        if (isRed(node.right) && !isRed(node.left)) node = leftRotate(node);     // 左旋转
        if (isRed(node.left) && isRed(node.left.left)) node = rightRotate(node); // 右旋转
        if (isRed(node.left) && isRed(node.right)) flipColors(node);             // 颜色翻转

        return node;
    }

    /**
     * 返回以 node 为根节点的红黑树中的最小元素所在的节点
     */
    private Node minimum(Node node)
    {
        if (node.left == null) return node;
        return minimum(node.left);
    }

    /**
     * 删除以 node 为根节点的红黑树的最小元素所在的节点, 并返回新的根节点
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
     * 以 node 为根节点的红黑树, 删除键为 key 所在的节点, 并返回新的根节点
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

    public boolean contains(K key)
    {
        return getNode(root, key) != null;
    }

    public V get(K key)
    {
        Node node = getNode(root, key);
        return node != null ? node.value : null;
    }

    public void set(K key, V newValue)
    {
        Node node = getNode(root, key);
        if (node != null) node.value = newValue;
        else throw new IllegalArgumentException(key + " doesn't exist!");
    }

    public int getSize()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }
}
