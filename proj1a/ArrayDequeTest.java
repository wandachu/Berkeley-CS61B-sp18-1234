import org.junit.Test;

/** Performs some basic ArrayDeque tests. */
public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        ArrayDeque<String> ad1 = new ArrayDeque<>();

        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, ad1.size()) && passed;
        passed = checkEmpty(false, ad1.isEmpty()) && passed;


        ad1.addLast("middle");
        passed = checkSize(2, ad1.size()) && passed;

        ad1.addLast("back");
        passed = checkSize(3, ad1.size()) && passed;

        ad1.addLast("back1");
        passed = checkSize(4, ad1.size()) && passed;

        ad1.addLast("back2");
        passed = checkSize(5, ad1.size()) && passed;

        ad1.addLast("back3");
        passed = checkSize(6, ad1.size()) && passed;

        ad1.addFirst("front-front");
        passed = checkSize(7, ad1.size()) && passed;

        String s = ad1.get(1);
        System.out.println(s);
        System.out.println("Printing out deque: ");
        ad1.printDeque();

        printTestStatus(passed);
    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        // should be empty
        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.removeFirst();
        // should be empty
        passed = checkEmpty(true, ad1.isEmpty()) && passed;

        ad1.addLast(100);
        // should not be empty
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.removeLast();
        // should be empty
        passed = checkEmpty(true, ad1.isEmpty()) && passed;

        printTestStatus(passed);

    }

    /** Adds a few items, then get the first item, the invalid index item, a middle item, and the last item,
     * using both recursive and iterative method.
     */
    public static void getItemTest() {
        System.out.println("Running getItem test.");
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        // should be empty
        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst("third");
        // should not be empty
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.addFirst("second");
        ad1.addFirst("first");
        ad1.addLast("last");
        ad1.printDeque();

        passed = (ad1.get(0).equals("first")) && passed;
        passed = (ad1.get(-1) == null) && passed;
        passed = (ad1.get(1).equals("second")) && passed;
        passed = (ad1.get(ad1.size() - 1).equals("last")) && passed;

        passed = (ad1.get(0).equals("first")) && passed;
        passed = (ad1.get(-1) == null) && passed;
        passed = (ad1.get(1).equals("second")) && passed;
        passed = (ad1.get(ad1.size() - 1).equals("last")) && passed;

        printTestStatus(passed);
    }


    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    public static void resizingAddTest() {
        System.out.println("Running adding with resizing test.");

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addLast(100);
        ad1.addLast(101);
        ad1.addLast(102);
        ad1.addLast(103);
        ad1.addLast(104);
        System.out.println("Next item will result in resizing");
        ad1.addFirst(3);
        ad1.addFirst(4);
        ad1.addLast(105);
        ad1.addLast(106);

        System.out.println("size should be 11. It's actually " + ad1.size());
        System.out.println("Printing out deque: ");
        ad1.printDeque();

    }

    public static void resizingRemovelTest() {
        System.out.println("Running removing with resizing test.");

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        boolean passed = checkEmpty(true, ad1.isEmpty());
        for (int i = 0; i < 100; i++) {
            ad1.addLast(1);
        }
        System.out.println("Complete adding");
        passed = (ad1.size() == 100) && passed;
        for (int i = 0; i < 90; i++) {
            ad1.removeLast();
        }
        System.out.println("Complete removing");
        passed = (ad1.size() == 10) && passed;

        ad1.printDeque();
        printTestStatus(passed);
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
		addIsEmptySizeTest();
		addRemoveTest();
        getItemTest();
        resizingAddTest();
        resizingRemovelTest();
    }

}
