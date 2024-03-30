package stage3.week9.heap;

import stage1.week3.array.Array;

/**
 * <p>二叉堆是一棵完全二叉树
 * <p>最大堆: 所有节点都大于等于孩子节点
 */
@SuppressWarnings("all")
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap(E[] arr) {
        heapify(arr);
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中, 一个索引所表示的元素的父亲节点的索引
     */
    private int parent(int index) {
        if (index == 0) throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中, 一个索引所表示的元素的左孩子节点的索引
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树的数组表示中, 一个索引所表示的元素的右孩子节点的索引
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 上浮: O(logN)
     */
    private void siftUp(int index) {
        while (index > 0 && data.get(index).compareTo(data.get(parent(index))) > 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    /**
     * 下沉: O(logN)
     */
    private void siftDown(int index) {
        while (leftChild(index) < data.getSize()) {
            int bigger = leftChild(index);
            if (bigger + 1 < data.getSize() && data.get(bigger + 1).compareTo(data.get(bigger)) > 0) bigger++;

            if (data.get(index).compareTo(data.get(bigger)) >= 0) break;
            data.swap(index, bigger);
            index = bigger;
        }
    }

    /**
     * <p>将任意数组整理成堆的形状: O(n)
     * <p>最后一个节点的父节点就是最后一个非叶子节点
     */
    private void heapify(E[] arr) {
        data = new Array<>(arr);
        for (int i = parent(data.getSize() - 1); i >= 0; i--) siftDown(i);
    }

    /**
     * O(logN)
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 查看堆中的最大元素
     */
    public E findMax() {
        if (isEmpty()) throw new RuntimeException("Heap is empty!");
        return data.get(0);
    }

    /**
     * 取出堆中的最大元素: O(logN)
     */
    public E extractMax() {
        E max = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return max;
    }

    /**
     * 取出堆中的最大元素, 并替换为一个新元素
     */
    public E replace(E e) {
        E max = findMax();
        data.set(0, e);
        siftDown(0);
        return max;
    }
}
