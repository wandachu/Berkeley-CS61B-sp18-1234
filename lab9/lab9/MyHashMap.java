package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Wanda Wang
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int index = hash(key);
        return buckets[index].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (loadFactor() > MAX_LF) {
            resize(buckets.length * 2);
        }
        int index = hash(key);
        ArrayMap<K, V> currMap = buckets[index];
        if (!currMap.containsKey(key)) {
            size++;
        }
        currMap.put(key, value);
    }

    private void resize(int capacity) {
        /* Initialize the new buckets array. */
        ArrayMap<K, V>[] newBuckets = new ArrayMap[capacity];
        for (int i = 0; i < capacity; i++) {
            newBuckets[i] = new ArrayMap<>();
        }
        /* Copy each item from the old map to the new one. */
        for (int i = 0; i < buckets.length; i++) {
            ArrayMap<K, V> currMap = buckets[i];
            if (currMap.size() > 0) {
                for (K key : currMap) {
                    int newIndex = Math.floorMod(key.hashCode(), capacity);
                    V value = currMap.get(key);
                    newBuckets[newIndex].put(key, value);
                }
            }
        }
        buckets = newBuckets;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            Set<K> currSet = buckets[i].keySet();
            for (K k : currSet) {
                keys.add(k);
            }
        }
        return keys;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        int index = hash(key);
        ArrayMap<K, V> currMap = buckets[index];
        V value = currMap.remove(key);
        return value;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        int index = hash(key);
        ArrayMap<K, V> currMap = buckets[index];
        V actualV = currMap.get(key);
        if (actualV.equals(value)) {
            currMap.remove(key);
            return value;
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        Set<K> keySet = keySet();
        return keySet().iterator();
    }
}
