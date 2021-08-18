public interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    T get(int index);
    boolean isEmpty();
    void printDeque();
    T removeFirst();
    T removeLast();
    int size();
}
