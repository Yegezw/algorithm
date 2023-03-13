package stage1.week3.queue;

import port.Queue;

/**
 * <p>循环队列
 * <p>队列为空: front == tail
 * <p>队列为满: (tail + 1) % length == front
 * <p>会浪费一个空间 data[tail]
 */
@SuppressWarnings("all")
public class LoopQueue<E> implements Queue<E> {
    
    private E[] data;
    private int front;
    private int tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = tail = size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) resize(getCapacity() * 2);
        
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
        return front == tail;
    }
    
    public int getCapacity() {
        return data.length - 1;
    }

    /**
     * 动态数组
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
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
        sb.append(String.format("LoopQueue: size = %d, capacity = %d\n", size, getCapacity()));
        sb.append("Front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            sb.append(data[i]);
            if ((i + 1) % data.length != tail) sb.append(", ");
        }
        // for (int i = 0; i < size; i++) {
        //     sb.append(data[(front + i) % data.length]);
        //     if (i != size - 1) sb.append(", ");
        // }
        sb.append("] Tail");
        return sb.toString();
    }
}
