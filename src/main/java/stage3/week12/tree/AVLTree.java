package stage3.week12.tree;

import java.util.ArrayList;

/**
 * <a href="https://my.oschina.net/xcafe/blog/5392904">深度分析 AVL 树的实现与优化</a>
 * <p>key 不能重复, 且必须可比较
 * <p>AVLTree 在添加和删除时保持自平衡
 */
@SuppressWarnings("all")
public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    /**
     * 获得节点 node 的高度
     */
    private int getHeight(Node node) {
        if (node == null) return 0;
        return node.height;
    }

    /**
     * 获得节点 node 的平衡因子
     */
    private int getBalanceFactor(Node node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 判断该二叉树是否是一棵二分搜索树
     */
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) return false;
        }
        return true;
    }

    /**
     * 中序遍历
     */
    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    /**
     * 判断该二叉树是否是一棵平衡二叉树
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * 判断该二叉树是否是一棵平衡二叉树, 递归算法
     */
    private boolean isBalanced(Node node) {
        if (node == null) return true;

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) return false;

        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 对节点 y 进行向右旋转操作, 返回旋转后新的根节点 x
     */
    //        y                              x
    //       / \                          /     \
    //      x   T4     向右旋转 (y)        z       y
    //     / \       - - - - - - - ->   /  \    /  \
    //    z   T3                       T1  T2  T3  T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node t3 = x.right;

        // 向右旋转过程
        x.right = y;
        y.left = t3;

        // 更新 height, 必须先更新 y, 后更新 x
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * 对节点 y 进行向左旋转操作, 返回旋转后新的根节点 x
     */
    //    y                              x
    //   / \                          /     \
    // T1   x      向左旋转 (y)        y       z
    //     / \   - - - - - - - ->   /  \    /  \
    //   T2   z                    T1  T2  T3  T4
    //       / \
    //     T3   T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node t2 = x.left;

        // 向左旋转过程
        x.left = y;
        y.right = t2;

        // 更新 height, 必须先更新 y, 后更新 x
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * 返回以 node 为根节点的 AVL 树中, 键为 key 所在的节点
     */
    private Node getNode(Node node, K key) {
        if (node == null) return null;

        if (key.compareTo(node.key) == 0) return node;
        if (key.compareTo(node.key) < 0) return getNode(node.left, key);
        return getNode(node.right, key);
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 向以 node 为根节点的 AVL 树中添加元素 (key, value), 并返回新的根节点
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0) node.right = add(node.right, key, value);
        else node.value = value;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1; // 更新 height

        int balanceFactor = getBalanceFactor(node); // 计算平衡因子
        // 平衡维护
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) return rightRotate(node);  // LL 右旋转
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) return leftRotate(node); // RR 左旋转
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            // LR 先左旋转, 再右旋转
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            // RL 先右旋转, 再左旋转
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /**
     * 返回以 node 为根节点的 AVL 树中的最小元素所在的节点
     */
    private Node minimum(Node node) {
        if (node.left == null) return node;
        return minimum(node.left);
    }

    /**
     * 删除以 node 为根节点的 AVL 树的最小元素所在的节点, 并返回新的根节点
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    /**
     * 以 node 为根节点的 AVL 树, 删除键为 key 所在的节点, 并返回新的根节点
     */
    private Node remove(Node node, K key) {
        if (node == null) return null;

        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                Node successor = minimum(node.right);
                // successor.right = removeMin(node.right); 可能会导致不平衡: (1)在 removeMin 中添加自平衡 (2)复用 remove
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }

        if (retNode == null) return null; // 删除叶子节点后, 返回的 retNode 就为 null
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1; // 更新 height

        int balanceFactor = getBalanceFactor(retNode); // 计算平衡因子
        // 平衡维护
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) return rightRotate(retNode);  // LL 右旋转
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) return leftRotate(retNode); // RR 左旋转
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            // LR 先左旋转, 再右旋转
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            // RL 先右旋转, 再左旋转
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node != null ? node.value : null;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node != null) node.value = newValue;
        else throw new IllegalArgumentException(key + " doesn't exist!");
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
