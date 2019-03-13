import java.util.Arrays;
import java.util.HashSet;

public class HashMap<K, V> {
    // Record the size of entries
    private int size;
    private Entry [] array;
    private HashSet<Entry<K, V>> entrySet;

    public HashMap(int capacity) {
        if(capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.array = new Entry[capacity];
        this.entrySet = new HashSet<Entry<K, V>>();
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public void clear() {
        Arrays.fill(this.array, null);
        this.size = 0;
    }

    public int hash(K key) {
        if(key == null) {
            return 0;
        }
        // Use & 0x7FFFFFFF (The maximum of integer) to make sure the result is positive
        return key.hashCode() & 0x7FFFFFFF;
    }

    public int getIndex(K key) {
        return hash(key) % this.array.length;
    }

    public Entry<K, V> getEntry(K key) {
        if(isEmpty()) {
            return null;
        }
        int index = getIndex(key);
        Entry<K, V> cur = this.array[index];
        while(cur != null) {
            if(cur.getKey().equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    // Case 1: key is already in the map, just change the value
    // Case 2: key is not in the map, add it to the head of linked list
    public void put(K key, V value) {
        int index = getIndex(key);
        Entry<K, V> entry = getEntry(key);
        if(entry != null) {
            entry.setValue(value);
            return ;
        }
        entry = new Entry<K, V>(key, value);
        entry.next = this.array[index];
        this.array[index] = entry;
        size++;
    }

    // If key is there, return the value. Otherwise return null
    public V get(K key) {
        Entry<K, V> cur = getEntry(key);
        return cur == null ? null : cur.getValue();
    }

    public void remove(K key) {
        int index = getIndex(key);
        Entry<K, V> cur = this.array[index];
        // Case 1: Nothing in the bucket
        if(cur == null) {
            return ;
        }
        // Case 2: Head is target to remove
        if(cur.getKey().equals(key)) {
            this.array[index] = cur.next;
            cur.next = null;
            size--;
            return ;
        }
        // Case 3: The target is in between linked list
        while(cur != null) {
            Entry<K, V> temp;
            if(cur.next.getKey().equals(key)) {
                temp = cur.next;
                cur.next = cur.next.next;
                temp.next = null;
            }
            cur = cur.next;
        }
        size--;
    }

    public boolean containsKey(K key) {
        return getEntry(key) != null;
    }

    public HashSet<Entry<K, V>> entrySet() {
        this.entrySet.clear();
        for(int i = 0; i < this.array.length; i++) {
            Entry<K, V> cur = this.array[i];
            while(cur != null) {
                entrySet.add(cur);
                cur = cur.next;
            }
        }
        return this.entrySet;
    }
}
