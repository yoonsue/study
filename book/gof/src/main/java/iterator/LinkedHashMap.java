package iterator;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;

public class LinkedHashMap<K,V> extends MyHashMap<K,V> implements MyMap<K,V> {
    final boolean accessOrder;

    public LinkedHashMap() {
        super();
        accessOrder = false;
    }

    static class Entry<K,V> extends MyHashMap.Node<K,V> {
        Entry<K,V> before, after;
        Entry(int hash, K key, V value, Node<K,V> next) {
            super(hash, key, value, next);
        }
    }

    transient LinkedHashMap.Entry<K,V> head;
    transient LinkedHashMap.Entry<K,V> tail;

    public void clear() {
        super.clear();
        head = tail = null;
    }

    @Override
    public Set<MyMap.Entry<K, V>> entrySet() {
        Set<MyMap.Entry<K,V>> es;
        return (es = entrySet) == null ? (entrySet = new LinkedEntrySet()) : es;
    }

    final class LinkedEntrySet extends AbstractSet<MyMap.Entry<K,V>> {
        public final int size()                 { return size; }
        public final void clear()               { LinkedHashMap.this.clear(); }
        public final Iterator<MyMap.Entry<K,V>> iterator() {
            return new LinkedEntryIterator();
        }
        public final boolean contains(Object o) {
            if (!(o instanceof MyMap.Entry))
                return false;
            MyMap.Entry<?,?> e = (MyMap.Entry<?,?>) o;
            Object key = e.getKey();
            Node<K,V> candidate = getNode(hash(key), key);
            return candidate != null && candidate.equals(e);
        }
        public final boolean remove(Object o) {
            if (o instanceof MyMap.Entry) {
                MyMap.Entry<?,?> e = (MyMap.Entry<?,?>) o;
                Object key = e.getKey();
                Object value = e.getValue();
                return removeNode(hash(key), key, value, true, true) != null;
            }
            return false;
        }
        public final Spliterator<MyMap.Entry<K,V>> spliterator() {
            return Spliterators.spliterator(this, Spliterator.SIZED |
                    Spliterator.ORDERED |
                    Spliterator.DISTINCT);
        }
        public final void forEach(Consumer<? super MyMap.Entry<K,V>> action) {
            if (action == null)
                throw new NullPointerException();
            int mc = modCount;
            for (LinkedHashMap.Entry<K,V> e = head; e != null; e = e.after)
                action.accept(e);
            if (modCount != mc)
                throw new ConcurrentModificationException();
        }
    }

    abstract class LinkedHashIterator {
        LinkedHashMap.Entry<K,V> next;
        LinkedHashMap.Entry<K,V> current;
        int expectedModCount;

        LinkedHashIterator() {
            next = head;
            expectedModCount = modCount;
            current = null;
        }

        public final boolean hasNext() {
            return next != null;
        }

        final LinkedHashMap.Entry<K,V> nextNode() {
            LinkedHashMap.Entry<K,V> e = next;
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (e == null)
                throw new NoSuchElementException();
            current = e;
            next = e.after;
            return e;
        }

        public final void remove() {
            Node<K,V> p = current;
            if (p == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            current = null;
            removeNode(p.hash, p.key, null, false, false);
            expectedModCount = modCount;
        }
    }

    void afterNodeAccess(Node<K,V> e) { // move node to last
        LinkedHashMap.Entry<K,V> last;
        if (accessOrder && (last = tail) != e) {
            LinkedHashMap.Entry<K,V> p =
                    (LinkedHashMap.Entry<K,V>)e, b = p.before, a = p.after;
            p.after = null;
            if (b == null)
                head = a;
            else
                b.after = a;
            if (a != null)
                a.before = b;
            else
                last = b;
            if (last == null)
                head = p;
            else {
                p.before = last;
                last.after = p;
            }
            tail = p;
            ++modCount;
        }
    }

    void afterNodeInsertion(boolean evict) { // possibly remove eldest
        LinkedHashMap.Entry<K,V> first;
        if (evict && (first = head) != null ) {
            K key = first.key;
            removeNode(hash(key), key, null, false, true);
        }
    }

    final class LinkedValueIterator extends LinkedHashIterator
            implements Iterator<V> {
        public final V next() { return nextNode().value; }
    }

    final class LinkedEntryIterator extends LinkedHashIterator
            implements Iterator<MyMap.Entry<K,V>> {
        public final MyMap.Entry<K,V> next() { return nextNode(); }
    }

}
