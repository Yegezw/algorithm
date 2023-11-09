package stage2.week8.bst;

import port.Queue;
import stage1.week3.queue.LoopQueue;

/**
 * <p>二分搜索树: Binary Search Tree
 * <p>存储的元素必须可比较
 * <p>对重复元素不做处理
 * <p>增、删、查: 最差 O(n), 平均 O(h), h = logN
 */
@SuppressWarnings("all")
public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 向以 node 为根节点的二分搜索树中添加元素 e, 并返回新的根节点
     */
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) node.left = add(node.left, e);
        else if (e.compareTo(node.e) > 0) node.right = add(node.right, e);

        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 在以 node 为根节点的二分搜索树中搜索是否存在元素 e
     */
    private boolean contains(Node node, E e) {
        if (node == null) return false;

        if (e.compareTo(node.e) == 0) return true;
        if (e.compareTo(node.e) < 0) return contains(node.left, e);
        return contains(node.right, e);
    }

    public E minimum() {
        if (size == 0) throw new RuntimeException("BST is empty!");
        return minimum(root).e;
    }

    /**
     * 返回以 node 为根节点的二分搜索树中的最小元素所在的节点
     */
    private Node minimum(Node node) {
        if (node.left == null) return node;
        return minimum(node.left);
    }

    public E maximum() {
        if (size == 0) throw new RuntimeException("BST is empty!");
        return maximum(root).e;
    }

    /**
     * 返回以 node 为根节点的二分搜索树中的最大元素所在的节点
     */
    private Node maximum(Node node) {
        if (node.right == null) return node;
        return maximum(node.right);
    }

    public E removeMin() {
        E min = minimum();
        root = removeMin(root);
        return min;
    }

    /**
     * 删除以 node 为根节点的二分搜索树的最小元素所在的节点, 并返回新的根节点
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

    public E removeMax() {
        E max = maximum();
        root = removeMax(root);
        return max;
    }

    /**
     * 删除以 node 为根节点的二分搜索树的最大元素所在的节点, 并返回新的根节点
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 以 node 为根节点的二分搜索树, 删除指定元素 e 所在的节点, 并返回新的根节点
     */
    private Node remove(Node node, E e) {
        if (node == null) return null;

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else {
                // 待删除节点左右子树均不为空
                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点, 它是后继节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right); // 已经 size-- 了
                successor.left = node.left;
                node.left = node.right = null;
                return successor;
            }
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    /**
     * 对以 node 为根节点的二分搜索树进行前序遍历
     */
    private void preOrder(Node node) {
        if (node == null) return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    /**
     * 对以 node 为根节点的二分搜索树进行中序遍历
     */
    private void inOrder(Node node) {
        if (node == null) return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    /**
     * 对以 node 为根节点的二分搜索树进行后序遍历
     */
    private void postOrder(Node node) {
        if (node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 二分搜索树的层序遍历
     */
    public void levelOrder() {
        Queue<Node> queue = new LoopQueue<>();
        queue.enqueue(root);

        Node cur;
        while (!queue.isEmpty()) {
            cur = queue.dequeue();
            System.out.println(cur.e);
            if (cur.left != null) queue.enqueue(cur.left);
            if (cur.right != null) queue.enqueue(cur.right);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        generateBSTString(root, 0, sb);
        return sb.toString();
    }

    /**
     * 生成以 node 为根节点, depth 为深度的描述二叉树的字符串
     */
    private void generateBSTString(Node node, int depth, StringBuilder sb) {
        if (node == null) {
            sb.append(generateDepthString(depth)).append("null\n");
            return;
        }

        sb.append(generateDepthString(depth)).append(node.e).append("\n");
        generateBSTString(node.left, depth + 1, sb);
        generateBSTString(node.right, depth + 1, sb);
    }

    private String generateDepthString(int depth) {
        return "--".repeat(Math.max(0, depth));
    }

    /**
     * <p>寻找 e 的 floor 值
     * <p>不存在时返回 null(e 比 BST 中的最小值还小)
     */
    public E floor(E e) {
        if (isEmpty() || e.compareTo(minimum()) < 0) return null;
        return floor(root, e).e;
    }

    /**
     * 在以 node 为根节点的二分搜索树中搜索元素 e 的 floor 节点
     */
    private Node floor(Node node, E e) {
        if (node == null) return null;

        // node.e == e
        // 则 node 本身就是 e 的 floor 节点
        if (node.e.compareTo(e) == 0) return node;

        // node.e > e
        // 则要寻找的 e 的 floor 节点一定在 node 的左子树中
        if (node.e.compareTo(e) > 0) return floor(node.left, e);

        // node.e < e
        // 则 node 有可能是 e 的 floor 节点, 也有可能不是(存在比 node.e 大但是小于 e 的其余节点)
        // 需要尝试向 node 的右子树寻找一下
        Node tempNode = floor(node.right, e);
        return tempNode != null ? tempNode : node;
    }

    /**
     * <p>寻找 e 的 ceil 值
     * <p>不存在时返回 null(e 比 BST 中的最大值还大)
     */
    public E ceil(E e) {
        if (size == 0 || e.compareTo(maximum()) > 0) return null;
        return ceil(root, e).e;
    }

    private Node ceil(Node node, E e) {
        if (node == null) return null;

        // node.e == e
        // 则 node 本身就是 e 的 ceil 节点
        if (node.e.compareTo(e) == 0) return node;

        // node.e < e
        // 则要寻找的 e 的 ceil 节点一定在 node 的右子树中
        if (node.e.compareTo(e) < 0) return ceil(node.right, e);

        // node.e > e
        // 则 node 有可能是 e 的 ceil 节点, 也有可能不是(存在比 node.e 小但是大于 e 的其余节点)
        // 需要尝试向 node 的左子树寻找一下
        Node tempNode = ceil(node.left, e);
        return tempNode != null ? tempNode : node;
    }
}
