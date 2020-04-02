package iterator;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;

public interface MyMap<K,V> {
    Set<Entry<K, V>> entrySet();

    interface Entry<K, V> {
        K getKey();

        V getValue();

        V setValue(V value);

        boolean equals(Object o);

        int hashCode();

        public static <K extends Comparable<? super K>, V> Comparator<MyMap.Entry<K, V>> comparingByKey() {
            return (Comparator<MyMap.Entry<K, V>> & Serializable)
                    (c1, c2) -> c1.getKey().compareTo(c2.getKey());
        }

        public static <K, V extends Comparable<? super V>> Comparator<MyMap.Entry<K, V>> comparingByValue() {
            return (Comparator<MyMap.Entry<K, V>> & Serializable)
                    (c1, c2) -> c1.getValue().compareTo(c2.getValue());
        }

        public static <K, V> Comparator<MyMap.Entry<K, V>> comparingByKey(Comparator<? super K> cmp) {
            Objects.requireNonNull(cmp);
            return (Comparator<MyMap.Entry<K, V>> & Serializable)
                    (c1, c2) -> cmp.compare(c1.getKey(), c2.getKey());
        }

        public static <K, V> Comparator<MyMap.Entry<K, V>> comparingByValue(Comparator<? super V> cmp) {
            Objects.requireNonNull(cmp);
            return (Comparator<MyMap.Entry<K, V>> & Serializable)
                    (c1, c2) -> cmp.compare(c1.getValue(), c2.getValue());
        }
    }
}
