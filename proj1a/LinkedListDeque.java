public class LinkedListDeque<T> {
    /** This is a private class nested within LinkedLIstDeque. */
    private class LLNode { //can't make this static because we're using generics???
        private LLNode prev; //should these methods be private??
        private T item;
        private LLNode next;

        private LLNode(T item) {
            prev = null;
            this.item = item;
            next = null;
        }
    }

    public LLNode sentinel;
    private int size;

    /**
     * Creates an empty LinkedListDeque.
     */
    public LinkedListDeque () {
        sentinel = new LLNode(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        this();
        addFirst(item);
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        LLNode newNode = new LLNode(item);
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        LLNode newNode = new LLNode(item);
        newNode.next = sentinel;
        newNode.prev = sentinel.prev;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the item in the deque from list to last, separated by a space.
     */
    public void printDeque() {
        LLNode curr = sentinel.next;
        while (curr != sentinel) {
            System.out.println(curr.item);
            curr = curr.next;
        }
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, return null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return first;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return last;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque.
     */
    public T get(int index) {
        if (index >= size() || index < 0) {
            return null;
        }
        LLNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index--;
        }
        return p.item;
    }

    /**
     * Same as the get method but using recursion.
     */
    public T getRecursive(int index) {
        if (index >= size() || index < 0) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    /**
     * Private helper method for get method using recursion.
     */
    private T getRecursiveHelper(int index, LLNode curr) {
        if (index == 0) {
            return curr.item;
        }
        return getRecursiveHelper(index - 1, curr.next);
    }

}
