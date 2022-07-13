package practice.basic;

import port.Queue;

/**
 * <p>使用 size, 不浪费一个空间
 * <p>队列为空: size == 0
 * <p>队列为满: size == data.length
 */
@SuppressWarnings("all")
public class LQ2<E> implements Queue<E> {

    private E[] data;
    private int front;
    private int tail;
    private int size;

    public LQ2(int capacity) {
        data = (E[]) new Object[capacity];
        front = tail = size = 0;
    }

    public LQ2() {
        this(10);
    }

    @Override
    public void enqueue(E e) {
        if (size == getCapacity()) resize(getCapacity() * 2);

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) throw new RuntimeException("队列为空");

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) resize(getCapacity() / 2);

        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) throw new RuntimeException("队列为空");
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int getCapacity() {
        return data.length;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("LQ2: size = %d, capacity = %d\n", size, getCapacity()));
        sb.append("Front [");
        for (int i = 0; i < size; i++) {
            sb.append(data[(i + front) % data.length]);
            if (i != size - 1) sb.append(", ");
        }
        sb.append("] Tail");
        return sb.toString();
    }

}
