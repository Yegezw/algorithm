package practice.basic;

/**
 * 练习动态数组
 */
@SuppressWarnings("all")
public class P2<E> {

    private E[] data;
    private int size;

    public P2(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public P2() {
        this(10);
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) throw new RuntimeException("0 <= index <= size");
        if (getSize() == getCapacity()) resize(getCapacity() * 2);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = e;
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E remove(int index) {
        if (index < 0 || index >= size) throw new RuntimeException("0 <= index < size");
        E ret = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) remove(index);
    }

    public void set(int index, E e) {
        if (index < 0 || index > size) throw new RuntimeException("0 <= index <= size");
        data[index] = e;
    }

    public E get(int index) {
        if (index < 0 || index >= size) throw new RuntimeException("0 <= index < size");
        return data[index];
    }

    public E getFrist() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public boolean contains(E e) {
        for (E one : data) {
            if (one.equals(e)) return true;
        }
        return false;
    }

    public int find(E e) {
        for (int i = 0; i < getSize(); i++) {
            if (data[i].equals(e)) return i;
        }
        return -1;
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
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("P2: size = %d, capacity = %d\n", getSize(), getCapacity()));
        sb.append('[');
        for (int i = 0; i < getSize(); i++) {
            sb.append(data[i]);
            if (i != getSize() - 1) sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }

}
