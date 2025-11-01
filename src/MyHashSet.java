import java.util.LinkedList;
import java.util.List;

public class MyHashSet<E> {
    private static final int CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private List<E>[] buckets;
    private int size;
    private int capacity;

    public MyHashSet() {
        this.capacity = CAPACITY;
        this.size = 0;
        this.buckets = new List[capacity];
    }

    private int hash(Object key) {
        int h = key.hashCode();
//        System.out.printf("Hash %s : %d\n", key, h);
        h = h == Integer.MIN_VALUE ? 0 : Math.abs(h);
        return h % capacity;
    }

    public boolean add(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Нельзя добавить Null значение");
        }

        if (size >= capacity * LOAD_FACTOR) {
            resize();
        }

        final int index = hash(e) % capacity;
//        System.out.printf("Add %s : %d\n", e, index);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        if (buckets[index].contains(e)) {
            return false;
        }

        buckets[index].add(e);
        size++;
        return true;
    }

    public boolean remove(E e) {
        if (e == null) {
            return false;
        }

        int index = hash(e) % capacity;
//        System.out.printf("Remove %s : %d\n", e, index);
        List<E> bucket = buckets[index];
        if (bucket == null) {
            return false;
        }

        boolean isRemoved = bucket.remove(e);
        if (isRemoved) {
            size--;
        }
        return isRemoved;
    }

    private void resize() {
        final List<E>[] oldBuckets = buckets.clone();
        capacity *= 2;
        buckets = new List[capacity];
        size = 0;

        for (List<E> bucket: oldBuckets) {
            if (bucket != null) {
                for (E item: bucket) {
                    add(item);
                }
            }
        }
    }

    public int size() {
        return size;
    }
}
