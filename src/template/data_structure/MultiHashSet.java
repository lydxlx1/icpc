package template.data_structure;

import java.util.*;

public class MultiHashSet<T> implements Set<T> {

    static class MyIterator<T> implements Iterator<T> {

        Iterator<Map.Entry<T, Integer>> mapIt;
        Map.Entry<T, Integer> entry;
        T key;
        int cnt;

        public MyIterator(Iterator<Map.Entry<T, Integer>> mapIt) {
            this.mapIt = mapIt;
        }

        @Override
        public boolean hasNext() {
            return entry != null || mapIt.hasNext();
        }

        @Override
        public T next() {
            if (entry == null) {
                entry = mapIt.next();
                key = entry.getKey();
                cnt = entry.getValue();
            }
            cnt--;
            if (cnt == 0) entry = null;
            return key;
        }

    }

    HashMap<T, Integer> map;
    int size = 0;

    public MultiHashSet() {
        map = new HashMap<>();
    }

    public MultiHashSet(int capacity) {
        map = new HashMap<>(capacity);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<T>(map.entrySet().iterator());
    }

    @Override
    public Object[] toArray() {
        Object[] res = new Object[size];
        int len = 0;
        for (Iterator<T> it = this.iterator(); it.hasNext();)
            res[len++] = it.next();
        return res;
    }

    @SuppressWarnings("hiding")
    @Override
    public <T> T[] toArray(T[] a) {
        try {
            throw new NullPointerException("Method not implemented.");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(T e) {
        map.compute(e, (k, v) -> v == null ? 1 : v + 1);
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(Object o) {
        map.compute((T) o, (k, v) -> v - 1);
        if (map.get(o) == 0) map.remove(o);
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c)
            if (!contains(o)) return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T o : c)
            add(o);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (Object o : c)
            if (!contains(o)) remove(o);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c)
            remove(o);
        return true;
    }

    @Override
    public void clear() {
        map.clear();
        size = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        else if (obj instanceof MultiHashSet) {
            @SuppressWarnings("unchecked")
            MultiHashSet<T> that = (MultiHashSet<T>) obj;
            return that.map.equals(map) && that.size == size;
        } else return false;
    }

    @Override
    public int hashCode() {
        int res = 17;
        res = res * 31 + map.hashCode();
        res = res * 31 + size;
        return res;
    }

    @Override
    public String toString() {
        return String.format("MultiSet: size = %d, content = %s", size, map.toString());
    }
}