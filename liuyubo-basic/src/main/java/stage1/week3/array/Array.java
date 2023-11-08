package stage1.week3.array;

import java.util.Arrays;

/**
 * 动态数组
 */
@SuppressWarnings("all")
public class Array<E> {

    private E[] data;
    private int size;

    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array() {
        this(10);
    }

    public Array(E[] arr) {
        data = Arrays.copyOf(arr, arr.length);
        size = arr.length;
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

    /**
     * 添加
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) throw new RuntimeException("need 0 <= index <= size");
        if (size == data.length) resize(data.length * 2);

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

    /**
     * 删除
     */
    public E remove(int index) {
        if (index < 0 || index >= size) throw new RuntimeException("need 0 <= index < size");

        E ret = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        data[size] = null;

        if (size == data.length / 4 && data.length / 2 != 0) resize(data.length / 2);
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

    /**
     * 修改
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) throw new RuntimeException("need 0 <= index < size");
        data[index] = e;
    }

    /**
     * 查看
     */
    public E get(int index) {
        if (index < 0 || index >= size) throw new RuntimeException("need 0 <= index < size");
        return data[index];
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) return true;
        }
        return false;
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) return i;
        }
        return -1;
    }

    /**
     * 动态数组
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    public void swap(int a, int b) {
        if (a < 0 || a >= size || b < 0 || b >= size) {
            throw new IllegalArgumentException("Swap failed, require 0 <= index < size");
        }
        E k = data[a];
        data[a] = data[b];
        data[b] = k;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }
}
