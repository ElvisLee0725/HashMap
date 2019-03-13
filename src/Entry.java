// An entry in hashmap is just like linked list node
public class Entry<K, V> {
    final K key;
    V val;
    Entry<K, V> next;

    public Entry(K key, V value) {
        this.key = key;
        this.val = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return val;
    }

    public V setValue(V value) {
        this.val = value;
        return this.val;
    }
}
