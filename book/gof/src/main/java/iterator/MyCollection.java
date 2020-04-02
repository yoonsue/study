package iterator;

import java.util.Iterator;
import java.util.function.IntFunction;

public interface MyCollection<E> extends Iterable<E> {
    int size();
    boolean isEmpty();
    boolean contains(Object o);

    Iterator<E> iterator();

    Object[] toArray();

    <T> T[] toArray(T[] a);

    default <T> T[] toArray(IntFunction<T[]> generator) {
        return toArray(generator.apply(0));
    }

    boolean add(E e);

    boolean remove(Object o);



}
