package state;

import lombok.SneakyThrows;

import java.util.Collection;
import java.util.Iterator;

// Context
public class LockedCollection implements Collection {
    private final Collection c;
    private int activeIterators = 0;

    private Unsafe active = new IsActive();
    private Unsafe locked = new IsLocked();
    private Unsafe state = active;

    public LockedCollection(Collection c) {
        this.c = c;
    }

    @Override
    public Iterator iterator() {
        final Iterator wrapped = c.iterator();
        ++activeIterators;
        state = locked;

        return new Iterator() {
            private boolean valid = true;

            @Override
            public boolean hasNext() {
                return wrapped.hasNext();
            }

            @Override
            public Object next() {
                Object next = wrapped.next();
                if (!hasNext()) {
                    if (--activeIterators == 0)
                        state = active;
                    valid = false;
                }
                return next;
            }
        };
    }

    public Unsafe getState() {
        return state;
    }

    // Request()
    @SneakyThrows(Exception.class)
    @Override
    public boolean add(Object o) {
        return state.add(o);
    }

    @SneakyThrows(Exception.class)
    @Override
    public boolean remove(Object o) {
        return state.remove(o);
    }

    // State
    private interface Unsafe {
        public boolean add(Object o) throws Exception;
        public boolean remove(Object o) throws Exception;
    }

    @Override
    public int size() {
        return c.size();
    }

    @Override
    public boolean isEmpty() {
        return c.isEmpty();
    }

    // Concrete State 1
    private final class IsActive implements Unsafe {

        @Override
        public boolean add(Object o) {
            return c.add(o);
        }

        @Override
        public boolean remove(Object o) {
            return c.remove(o);
        }
    }

    // Concrete State 2
    private final class IsLocked implements Unsafe {

        @SneakyThrows(Exception.class)
        @Override
        public boolean add(Object o) {
            throw new Exception("locked");
        }

        @SneakyThrows(Exception.class)
        @Override
        public boolean remove(Object o) {
            throw new Exception("locked");
        }
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
