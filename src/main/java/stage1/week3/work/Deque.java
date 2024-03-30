package stage1.week3.work;

/**
 * <p>双端队列
 * <p>使用 size, 不浪费空间
 * <p>队列为空: size == 0
 * <p>队列为满: size == length
 */
@SuppressWarnings("all")
public class Deque<E> {

    private E[] data;
    private int front;
    private int tail;
    private int size;

    public Deque(int capacity) {
        data = (E[]) new Object[capacity];
        front = tail = size = 0;
    }

    public Deque() {
        this(10);
    }

    public void addFront(E e) {
        if (size == data.length) resize(data.length * 2);

        front = (front != 0) ? (front - 1) : (data.length - 1);
        data[front] = e;
        size++;
    }

    public void addLast(E e) {
        if (size == data.length) resize(data.length * 2);

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    public E removeFront() {
        if (isEmpty()) throw new RuntimeException("队列为空");

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        if (size == data.length / 4 && data.length / 2 != 0) resize(data.length / 2);
        return ret;
    }

    public E removeLast() {
        if (isEmpty()) throw new RuntimeException("队列为空");

        tail = (tail != 0) ? (tail - 1) : (data.length - 1);
        E ret = data[tail];
        data[tail] = null;
        size--;

        if (size == data.length / 4 && data.length / 2 != 0) resize(data.length / 2);
        return ret;
    }

    public E getFront() {
        if (isEmpty()) throw new RuntimeException("队列为空");
        return data[front];
    }

    public E getLast() {
        if (isEmpty()) throw new RuntimeException("队列为空");
        return (tail != 0) ? data[tail - 1] : data[data.length - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
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
        sb.append(String.format("Deque: size = %d, capacity = %d\n", getSize(), getCapacity()));
        sb.append('[');
        for (int i = 0; i < getSize(); i++) {
            sb.append(data[(i + front) % data.length]);
            if (i != getSize() - 1) sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }
}
