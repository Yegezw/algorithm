package stage1.week3.work;

import port.Queue;

/**
 * <p>循环队列
 * <p>使用 size, 不浪费空间
 * <p>队列为空: size == 0
 * <p>队列为满: size == length
 */
@SuppressWarnings("all")
public class LoopQueue2<E> implements Queue<E>
{

    private E[] data;
    private int front;
    private int tail;
    private int size;

    public LoopQueue2(int capacity)
    {
        data  = (E[]) new Object[capacity];
        front = tail = size = 0;
    }

    public LoopQueue2()
    {
        this(10);
    }

    @Override
    public void enqueue(E e)
    {
        if (size == data.length) resize(data.length * 2);

        data[tail] = e;
                     tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue()
    {
        if (isEmpty()) throw new RuntimeException("队列为空");

        E ret = data[front];
        data[front] = null;
        front       = (front + 1) % data.length;
        size--;

        if (size == data.length / 4 && data.length / 2 != 0) resize(data.length / 2);
        return ret;
    }

    @Override
    public E getFront()
    {
        if (isEmpty()) throw new RuntimeException("队列为空");
        return data[front];
    }

    @Override
    public int getSize()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    public int getCapacity()
    {
        return data.length;
    }

    /**
     * 动态数组
     */
    private void resize(int newCapacity)
    {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++)
        {
            newData[i] = data[(i + front) % data.length];
        }
        data  = newData;
        front = 0;
        tail  = size;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("LoopQueue2: size = %d, capacity = %d\n", size, getCapacity()));
        sb.append("Front [");
        for (int i = 0; i < size; i++)
        {
            sb.append(data[(i + front) % data.length]);
            if (i != size - 1) sb.append(", ");
        }
        sb.append("] Tail");
        return sb.toString();
    }
}
