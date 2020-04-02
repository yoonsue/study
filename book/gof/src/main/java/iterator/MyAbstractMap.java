package iterator;

import java.util.Set;

public abstract class MyAbstractMap<K,V> implements MyMap<K,V>{
    transient Set<K> keySet;
    transient MyCollection<V> value;

    @Override
    public abstract Set<Entry<K,V>> entrySet();
}
