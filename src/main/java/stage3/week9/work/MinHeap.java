package stage3.week9.work;

import stage1.week3.array.Array;

/**
 * <p>二叉堆是一棵完全二叉树
 * <p>最小堆: 所有节点都小于等于孩子节点
 */
@SuppressWarnings("all")
public class MinHeap<E extends Comparable<E>>
{

    private Array<E> data;

    public MinHeap()
    {
        data = new Array<>();
    }

    public MinHeap(int capacity)
    {
        data = new Array<>(capacity);
    }

    public MinHeap(E[] arr)
    {
        heapify(arr);
    }

    public int size()
    {
        return data.getSize();
    }

    public boolean isEmpty()
    {
        return data.isEmpty();
    }

    private int parent(int index)
    {
        if (index == 0) throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    private int leftChild(int index)
    {
        return index * 2 + 1;
    }

    private int rightChild(int index)
    {
        return index * 2 + 2;
    }

    private void siftUp(int index)
    {
        while (index > 0 && data.get(index).compareTo(data.get(parent(index))) < 0)
        {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    private void siftDown(int index)
    {
        while (leftChild(index) < data.getSize())
        {
            int smaller = leftChild(index);
            if (smaller + 1 < data.getSize() && data.get(smaller + 1).compareTo(data.get(smaller)) < 0) smaller++;

            if (data.get(index).compareTo(data.get(smaller)) <= 0) break;
            data.swap(index, smaller);
            index = smaller;
        }
    }

    private void heapify(E[] arr)
    {
        data = new Array<>(arr);
        for (int i = parent(data.getSize() - 1); i >= 0; i--) siftDown(i);
    }

    public void add(E e)
    {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    public E findMin()
    {
        if (isEmpty()) throw new RuntimeException("Heap is empty!");
        return data.get(0);
    }

    public E extractMin()
    {
        E min = findMin();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return min;
    }

    public E replace(E e)
    {
        E min = findMin();
        data.set(0, e);
        siftDown(0);
        return min;
    }
}
