import java.util.Arrays;
import java.util.Collection;

public class MyArrayList<E> {
    private static final Object[] DEFAULT_DATA = {};
    private static final int DEFAULT_CAPACITY = 8;

    private int size;
    private Object[] data;

    public MyArrayList() {
        this.data = DEFAULT_DATA;
    }

    public MyArrayList(int c) {
        if (c == 0) {
            this.data = DEFAULT_DATA;
        }
        else if (c > 0) {
            this.data = new Object[c];
        }
        else {
            throw new IllegalArgumentException("Размерность не может быть отрицательной");
        }
    }

    public boolean add(E e) {
        if (size == data.length) {
            data = resize(size + 1);
        }
        data[size] = e;
        size++;
        return true;
    }

    public boolean addAll(Collection<? extends E> ea) {
        Object[] newItems = ea.toArray();
        final int numItems = newItems.length;
        if (numItems == 0) {
            return false;
        }
        if (numItems > data.length - size) {
            data = resize(data.length - size);
        }
        System.arraycopy(newItems, 0, data, size, numItems);
        size += numItems;
        return true;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public E remove(int index) {
        checkIndex(index);
        final Object[] oldData = data;
        final E oldValue = (E)oldData[index];
//        System.out.println(oldValue);
        final int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(oldData, index + 1, data, index, newSize - index);
        }
        oldData[newSize] = null;
        size = newSize;
        return oldValue;
    }

    public E remove(E e) {
        final int index;
        int i = 0;
        if (e == null) {
            for (; i < data.length; i++) {
                if (data[i] == null) {
                    break;
                }
            }
        }
        else {
            for (; i < data.length; i++) {
                if (data[i].equals(e)) {
                    break;
                }
            }
        }
        index = i;
        return remove(index);
    }

    public E get(int index) {
        checkIndex(index);
        return (E)data[index];
    }

    private Object[] resize(int s) {
        int oldCapacity = data.length;
        if (oldCapacity > 0) {
            int newCapacity = Math.clamp((long)oldCapacity, s - oldCapacity, oldCapacity * 2);
            return data = Arrays.copyOf(data, newCapacity);
        }
        else {
            return data = new Object[Math.max(s, DEFAULT_CAPACITY)];
        }
    }

    public int size() {
        return this.size;
    }
}
