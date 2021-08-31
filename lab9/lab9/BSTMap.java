package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Wanda Wang
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        /* Size of nodes including this node and all its children. */
        private int size;

        private Node(K k, V v) {
            key = k;
            value = v;
            size = 1;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp == 0) {
            return p.value;
        } else if (cmp < 0) {
            return getHelper(key, p.left);
        } else {
            return getHelper(key, p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(p.key);
        if (cmp == 0) {
            p.value = value;
        } else if (cmp < 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.right = putHelper(key, value, p.right);
        }
        p.size = 1 + size(p.left) + size(p.right);
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("can't add a null key");
        }
        if (value == null) {
            remove(key);
            return;
        }
        root = putHelper(key, value, root); // must say "root ="
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node p) {
        if (p == null) {
            return 0;
        }
        return p.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        if (size() == 0) {
            return keySet;
        }
        setHelper(root, keySet);
        return keySet;
    }

    private void setHelper(Node curr, Set<K> s) {
        if (curr == null) {
            return;
        }
        setHelper(curr.left, s);
        s.add(curr.key);
        setHelper(curr.right, s);
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        V value = get(key);
        return remove(key, value);
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (key == null || size() == 0) {
            return null;
        }
        int beforeSize = size();
        root = removeHelper(key, value, root);
        int afterSize = size();
        if (beforeSize > afterSize) {
            return value;
        }
        return null;
    }

    private Node removeHelper(K key, V value, Node curr) {
        if (curr == null) {
            return null;
        }
        int cmp = key.compareTo(curr.key);
        if (cmp < 0) {
            curr.left = removeHelper(key, value, curr.left);
        } else if (cmp > 0) {
            curr.right = removeHelper(key, value, curr.right);
        } else {
            if (!curr.value.equals(value)) {
                return curr;
            }
            if (curr.left == null) {
                return curr.right;
            }
            if (curr.right == null) {
                return curr.left;
            }
            // need to find the smallest item in its right arm to replace it.
            Node temp = curr;
            curr = min(temp.right);
            // need to delete that smallest item.
            curr.right = deleteMin(temp.right);
            curr.left = temp.left;
        }
        curr.size = 1 + size(curr.left) + size(curr.right);
        return curr;
    }

    private Node min(Node curr) {
        if (curr.left == null) {
            return curr;
        }
        return min(curr.left);
    }

    private Node deleteMin(Node curr) {
        if (curr.left == null) {
            return curr.right;
        }
        curr.left = deleteMin(curr.left);
        curr.size = 1 + size(curr.left) + size(curr.right);
        return curr;
    }

    @Override
    public Iterator<K> iterator() {
        Set<K> keySet = keySet();
        return keySet.iterator();
    }
}
