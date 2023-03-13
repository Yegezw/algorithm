package stage1.week3.work;

import port.Queue;

/**
 * <p>循环队列
 * <p>不使用 size, 浪费一个空间 data[tail]
 * <p>队列为空: front == tail
 * <p>队列为满: (tail + 1) % length == front
 * <p>size = (tail >= front) ? (tail - front) : (tail - front + length)
 */
@SuppressWarnings("all")
public class LoopQueue3<E> implements Queue<E> {

    private E[] data;
    private int front;
    private int tail;

    public LoopQueue3(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = tail = 0;
    }

    public LoopQueue3() {
        this(10);
    }

    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) resize(getCapacity() * 2);
        
        data[tail] = e;
        tail = (tail + 1) % data.length;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) throw new RuntimeException("队列为空");
        
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        
        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) resize(getCapacity() / 2);
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) throw new RuntimeException("队列为空");
        return data[front];
    }

    @Override
    public int getSize() {
        return (tail >= front) ? (tail - front) : (tail - front + data.length);
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
        for (int i = 0; i < getSize(); i++) {
            newData[i] = data[(i + front) % data.length];
        }
        tail = getSize();
        front = 0;
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("LoopQueue3: size = %d, capacity = %d\n", getSize(), getCapacity()));
        sb.append("Front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            sb.append(data[i]);
            if ((i + 1) % data.length != tail) sb.append(", ");
        }       
        sb.append("] Tail");
        return sb.toString();
    }
}
