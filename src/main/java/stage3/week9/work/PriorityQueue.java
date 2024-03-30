package stage3.week9.work;

import port.Queue;

/**
 * 基于最小堆实现的优先队列
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private final MinHeap<E> minHeap;

    public PriorityQueue() {
        minHeap = new MinHeap<>();
    }

    @Override
    public void enqueue(E e) {
        minHeap.add(e);
    }

    @Override
    public E dequeue() {
        return minHeap.extractMin();
    }

    @Override
    public E getFront() {
        return minHeap.findMin();
    }

    @Override
    public int getSize() {
        return minHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return minHeap.isEmpty();
    }
}
