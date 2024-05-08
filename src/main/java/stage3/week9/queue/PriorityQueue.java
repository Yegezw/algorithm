package stage3.week9.queue;

import port.Queue;
import stage3.week9.heap.MaxHeap;

/**
 * 基于最大堆实现的优先队列
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E>
{

    private final MaxHeap<E> maxHeap;

    public PriorityQueue()
    {
        maxHeap = new MaxHeap<>();
    }

    @Override
    public void enqueue(E e)
    {
        maxHeap.add(e);
    }

    @Override
    public E dequeue()
    {
        return maxHeap.extractMax();
    }

    @Override
    public E getFront()
    {
        return maxHeap.findMax();
    }

    @Override
    public int getSize()
    {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty()
    {
        return maxHeap.isEmpty();
    }
}
