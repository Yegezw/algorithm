package stage1.week3.queue;

import port.Queue;
import stage1.week3.array.Array;

/**
 * 数组队列
 */
@SuppressWarnings("all")
public class ArrayQueue<E> implements Queue<E> {

    private final Array<E> array;

    public ArrayQueue() {
        this.array = new Array<>();
    }

    public ArrayQueue(int capacity) {
        this.array = new Array<>(capacity);
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayQueue: Front [");
        for (int i = 0; i < array.getSize(); i++) {
            sb.append(array.get(i));
            if (i != array.getSize() - 1) sb.append(", ");
        }
        sb.append("] Tail");
        return sb.toString();
    }
}
