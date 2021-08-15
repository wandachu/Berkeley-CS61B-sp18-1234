public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int capacity = 8;
    private static final int RFACTOR = 2;
    private static final double RESIZERATIO = 0.25;

    /**
     * Creates an empty ArrayListDeque.
     */
    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (size == capacity - 1) { //start resizing one item earlier
            resize(capacity * RFACTOR);
        }
        items[nextFirst] = item;
        nextFirst = Math.floorMod(nextFirst - 1 + capacity, capacity);
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (size == capacity - 1) { //start resizing one item earlier
            resize(capacity * RFACTOR);
        }
        items[nextLast] = item;
        nextLast = Math.floorMod(nextLast + 1 + capacity, capacity);
        size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the item in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        int count = size;
        int i = Math.floorMod(nextFirst + 1 + capacity, capacity);
        while (count > 0) {
            System.out.print(items[i] + " ");
            i = Math.floorMod(i + 1 + capacity, capacity);
            count--;
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, return null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = Math.floorMod(nextFirst + 1 + capacity, capacity);
        T res = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if (size >= 8 && (size / (double) capacity < RESIZERATIO)) {
            resize(size * RFACTOR);
        }
        return res;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = Math.floorMod(nextLast - 1 + capacity, capacity);
        T res = items[nextLast];
        items[nextLast] = null;
        size--;
        if (size >= 8 && (size / (double) capacity < RESIZERATIO)) {
            resize(size * RFACTOR);
        }
        return res;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque.
     */
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int p = nextFirst;
        while (index >= 0) {
            p = Math.floorMod(p + 1 + capacity, capacity);
            index--;
        }
        return items[p];
    }

    /**
     * Resizes the item array if the array is almost full.
     */
    private void resize(int newSize) {
        T[] a = (T[]) new Object[newSize];
        int index = Math.floorMod(nextFirst + 1 + capacity, capacity);
        for (int i = 0; i < size; i++) {
            a[i] = items[index];
            index = Math.floorMod(index + 1 + capacity, capacity);
        } //when the loop is over, index is at the empty place that NextLast goes.
        nextFirst = newSize - 1;
        nextLast = size;
        items = a;
        capacity = newSize;
    }

}
