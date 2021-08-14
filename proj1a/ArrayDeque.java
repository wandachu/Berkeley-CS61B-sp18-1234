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
            capacity *= RFACTOR;
        }
        items[nextFirst] = item;
        updateNextFirst();
        size++;
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
    private void resize(int c) {
        T[] a = (T[]) new Object[c];

        int index = nextFirst + 1;
        if (index == capacity) {
            index = 0;
        }
        for (int i = 0; i < size; i++) {
            a[i] = items[index];
            if (index == c / RFACTOR - 1) {
                index = 0;
            } else {
                index++;
            }
        } //when the for loop is over, index is at the empty place that NL should go.
        nextFirst = c - 1;
        nextLast = size;
        items = a;
    }


    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (size == capacity - 1) { //start resizing one item earlier
            resize(capacity * RFACTOR);
            capacity *= RFACTOR;
        }
        items[nextLast] = item;
        updateNextLast();
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
        int i = nextFirst + 1;
        if (i == capacity) {
            i = 0;
        }
        while (count > 0) {
            if (count == 0) {
                System.out.print(items[i]);
                break;
            }
            System.out.print(items[i] + " ");
            if (i == capacity - 1) {
                i = 0;
            } else {
                i++;
            }
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
        if (nextFirst == capacity - 1) {
            nextFirst = 0;
        } else {
            nextFirst++;
        }
        T res = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if (size != 0) {
            if (size / (double) capacity < RESIZERATIO) {
                resize(size * RFACTOR);
                capacity = size * RFACTOR;
            }
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
        if (nextLast == 0) {
            nextLast = capacity - 1;
        } else {
            nextLast--;
        }
        T res = items[nextLast];
        items[nextLast] = null;
        size--;
        if (size != 0) {
            if (size / (double) capacity < RESIZERATIO) {
                resize(size * RFACTOR);
                capacity = size * RFACTOR;
            }
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
