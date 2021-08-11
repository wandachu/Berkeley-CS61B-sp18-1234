public class SLList {
    private static class IntNode {
        private int item;
        private IntNode next;

        private IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;
    public SLList(int x) {
        this();
        addFirst(x);
    }

    public SLList() {
        sentinel = new IntNode(324, null);
        size = 0;
    }

    public SLList(int[] intArray) {
        this();
        for (int i = 0; i < intArray.length; i++) {
            addLast(intArray[i]);
        }
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size++;
    }

    /** Retrieves the front item from the list. */
    public int getFirst() {
        return sentinel.next.item;
    }

    /** Adds an item to the end of the list. */
    public void addLast(int x) {
        //Iterative method
//        IntNode curr = sentinel;
//        while (curr.next != null) {
//            curr = curr.next;
//        }
//        curr.next = new IntNode(x, null);

        //Recursive method
        addLastHelper(x, sentinel);
        size++;
    }

    private void addLastHelper(int x, IntNode curr) {
        if (curr.next == null) {
            curr.next = new IntNode(x, null);
        } else {
            addLastHelper(x, curr.next);
        }
    }

    /** Returns the number of items in the list using recursion. */
    public int size() {
        // Iterative method
//        int size = 0;
//        IntNode curr = sentinel.next;
//        while (curr != null) {
//            size++;
//            curr = curr.next;
//        }
//        return size;

        // Recursive method
//        return size(first);

        // Using caching
        return size;
    }

    /** Returns the size of the list starting at IntNode p. */
    private static int size(IntNode p) { //overloaded
        if (p.next == null) {
            return 1;
        }
        return 1 + size(p.next);
    }

    /** Deletes teh first element in the LList. */
    public void deleteFirst() {
        if (sentinel.next == null) {
            return;
        }
        size--;
        sentinel.next = sentinel.next.next;
    }


    public static void main(String[] args) {
//        SLList L = new SLList(15);
//        SLList L = new SLList();
        int[] array = {1, 2, 3, 4};
        SLList L = new SLList(array);
//        L.addFirst(10);
//        L.addFirst(5);
//        L.addLast(1);
//        L.deleteFirst();
        System.out.println(L.size());
    }

    /*  A SLList with a sentinel node has at least the following invariants:
        The sentinel reference always points to a sentinel node.
        The front item (if it exists), is always at sentinel.next.item.
        The size variable is always the total number of items that have been added.
     */

}
