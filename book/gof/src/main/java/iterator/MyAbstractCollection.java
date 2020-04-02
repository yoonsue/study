package iterator;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Iterator;

public abstract class MyAbstractCollection<E> implements MyCollection<E> {
    protected MyAbstractCollection() {

    }

    public abstract Iterator<E> iterator();

    public abstract int size();

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object o) {
        Iterator<E> it = iterator();
        if (o==null) {
            while (it.hasNext())
                if (it.next()==null)
                    return true;
        } else {
            while (it.hasNext())
                if (o.equals(it.next()))
                    return true;
        }
        return false;
    }

    public Object[] toArray() {
        Object[] r = new Object[size()];
        Iterator<E> it = iterator();
        for (int i = 0; i < r.length; i++) {
            if (! it.hasNext()) // fewer elements than expected
                return Arrays.copyOf(r, i);
            r[i] = it.next();
        }
        return it.hasNext() ? finishToArray(r, it) : r;
    }

    private static <T> T[] finishToArray(T[] r, Iterator<?> it) {
        int len = r.length;
        int i = len;
        while (it.hasNext()) {
            if (i == len) {
                len = newLength(len,
                        1,             /* minimum growth */
                        (len >> 1) + 1 /* preferred growth */);
                r = Arrays.copyOf(r, len);
            }
            r[i++] = (T)it.next();
        }
        // trim if overallocated
        return (i == len) ? r : Arrays.copyOf(r, i);
    }

    private static final int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;

    public static int newLength(int oldLength, int minGrowth, int prefGrowth) {
        // assert oldLength >= 0
        // assert minGrowth > 0

        int newLength = Math.max(minGrowth, prefGrowth) + oldLength;
        if (newLength - MAX_ARRAY_LENGTH <= 0) {
            return newLength;
        }
        return hugeLength(oldLength, minGrowth);
    }

    private static int hugeLength(int oldLength, int minGrowth) {
        int minLength = oldLength + minGrowth;
        if (minLength < 0) { // overflow
            throw new OutOfMemoryError("Required array length too large");
        }
        if (minLength <= MAX_ARRAY_LENGTH) {
            return MAX_ARRAY_LENGTH;
        }
        return Integer.MAX_VALUE;
    }

    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object o) {
        Iterator<E> it = iterator();
        if (o==null) {
            while (it.hasNext()) {
                if (it.next()==null) {
                    it.remove();
                    return true;
                }
            }
        } else {
            while (it.hasNext()) {
                if (o.equals(it.next())) {
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }
}
