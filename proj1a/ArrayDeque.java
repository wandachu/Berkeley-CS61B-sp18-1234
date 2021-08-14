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
            capacity *= 2;
            resize(capacity);
        }
        size++;
        items[nextFirst] = item;
        updateNextFirst();
    }

    /**
     * Updates the nextFirst pointer.
     */
    private void updateNextFirst() {
        if (nextFirst == 0) {
            nextFirst = capacity - 1; //no need to resize since we already consider that
        } else {
            nextFirst--;
        }
    }

    /**
     * Updates the nextLast pointer.
     */
    private void updateNextLast() {
        if (nextLast == capacity - 1) {
            nextLast = 0; //no need to resize since we already consider that
        } else {
            nextLast++;
        }
    }

    /**
     * Resizes the item array if the array is full.
     */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a ,0, size);
        items = a;
    }


    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (size == capacity - 1) { //start resizing one item earlier
            capacity *= 2;
            resize(capacity);
        }
        size++;
        items[nextLast] = item;
        updateNextLast();
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
        for (int i = nextFirst + 1; i < nextFirst + size; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println(items[nextFirst + size]);
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, return null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size / (double) capacity < RESIZERATIO) {
            capacity *= RFACTOR;
            resize(capacity);
        }
        if (nextFirst == capacity - 1) {
            nextFirst = 0;
        } else {
            nextFirst++;
        }
        T res = items[nextFirst];
        items[nextFirst] = null;
        size--;
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
        if (size / (double) capacity < RESIZERATIO) {
            capacity *= RFACTOR;
            resize(capacity);
        }
        if (nextLast == 0) {
            nextLast = capacity - 1;
        } else {
            nextLast--;
        }
        T res = items[nextLast];
        items[nextLast] = null;
        size--;
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
            if (p == capacity - 1) {
                p = 0;
            } else {
                p++;
            }
            index--;
        }
        return items[p];
    }

}
