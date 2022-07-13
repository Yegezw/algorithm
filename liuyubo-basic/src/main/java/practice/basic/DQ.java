package practice.basic;

/**
 * <p>使用 size, 不浪费一个空间
 * <p>队列为空: size == 0
 * <p>队列为满: size == data.length
 */
@SuppressWarnings("all")
public class DQ<E> {

    private E[] data;
    private int front;
    private int tail;
    private int size;

    public DQ(int capacity) {
        data = (E[]) new Object[capacity];
        front = tail = size = 0;
    }

    public DQ() {
        this(10);
    }

    public void addFront(E e) {
        if (getSize() == getCapacity()) resize(getCapacity() * 2);

        front = front != 0 ? front - 1 : data.length - 1;
        data[front] = e;
        size++;
    }

    public void addLast(E e) {
        if (getSize() == getCapacity()) resize(getCapacity() * 2);

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

        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) resize(getCapacity() / 2);
        return ret;
    }

    public E removeLast() {
        if (isEmpty()) throw new RuntimeException("队列为空");

        tail = tail != 0 ? tail - 1 : data.length - 1;
        E ret = data[tail];
        data[tail] = null;
        size--;

        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) resize(getCapacity() / 2);
        return ret;
    }

    public E getFront() {
        if (isEmpty()) throw new RuntimeException("队列为空");
        return data[front];
    }

    public E getLast() {
        if (isEmpty()) throw new RuntimeException("队列为空");
        return tail != 0 ? data[tail - 1] : data[data.length - 1];
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < getSize(); i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = getSize();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("DQ: size  = %d, capacity = %d\n", getSize(), getCapacity()));
        sb.append('[');
        for (int i = 0; i < getSize(); i++) {
            sb.append(data[(i + front) % data.length]);
            if (i != getSize() - 1) sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }

}
