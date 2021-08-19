import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void testArrayDeque() {
        //Test addFirst()
        System.out.println("Testing on addFirst...");
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        assertTrue(ads1.isEmpty());
        assertTrue(sad1.isEmpty());

        for (int i = 0; i < 50; i++) {
            int randomAdd = StdRandom.uniform(20);
            sad1.addFirst(randomAdd);
            ads1.addFirst(randomAdd);
        }

        for (int i = 0; i < 50; i++) {
            Integer expected = ads1.get(i);
            Integer actual = sad1.get(i);
            assertEquals("Test of addFirst failed on the "
                    + i + " item. Expected " + expected + " but got " + actual, expected, actual);
        }


        //Test addLast()
        System.out.println("Testing on addLast...");
        StudentArrayDeque<Integer> sad2 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads2 = new ArrayDequeSolution<>();

        for (int i = 0; i < 50; i++) {
            int randomAdd = StdRandom.uniform(20);
            sad2.addLast(randomAdd);
            ads2.addLast(randomAdd);
        }

        for (int i = 0; i < 50; i++) {
            Integer expected = ads2.get(i);
            Integer actual = sad2.get(i);
            assertEquals("Test of addLast failed on the "
                    + i + " item. Expected " + expected + " but got " + actual, expected, actual);
        }


        //Test removeFirst()
        System.out.println("Testing on removeFirst...");
        StudentArrayDeque<Integer> sad3 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads3 = new ArrayDequeSolution<>();

        assertTrue(ads3.isEmpty());
        assertTrue(sad3.isEmpty());

        /* add one item to the Deque so it's not empty.*/
        sad3.addFirst(5);
        ads3.addFirst(5);
        System.out.println("addFirst(5)");

        for (int i = 0; i < 9; i++) {
            int randomAdd = StdRandom.uniform(50);
            if (randomAdd < 25) {
                sad3.addFirst(randomAdd);
                ads3.addFirst(randomAdd);
                System.out.println("addFirst(" + randomAdd + ")");
            } else {
                sad3.addLast(randomAdd);
                ads3.addLast(randomAdd);
                System.out.println("addLast(" + randomAdd + ")");
            }
            System.out.println("removeFirst()");
            Integer expected = ads3.removeFirst();
            Integer actual = sad3.removeFirst();
            assertEquals("removeFirst()", expected, actual);
            System.out.println("addLast(" + expected + ")");
            ads3.addLast(expected);
            sad3.addLast(actual);
        }
        assertEquals(sad3.size(), ads3.size());


        //Test removeLast()
        System.out.println("Testing on removeLast...");
        StudentArrayDeque<Integer> sad4 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads4 = new ArrayDequeSolution<>();

        assertTrue(ads4.isEmpty());
        assertTrue(sad4.isEmpty());

        /* add one item to the Deque so it's not empty.*/
        sad4.addFirst(5);
        ads4.addFirst(5);
        System.out.println("addFirst(5)");

        for (int i = 0; i < 9; i++) {
            int randomAdd = StdRandom.uniform(50);
            if (randomAdd < 25) {
                sad4.addFirst(randomAdd);
                ads4.addFirst(randomAdd);
                System.out.println("addFirst(" + randomAdd + ")");
            } else {
                sad4.addLast(randomAdd);
                ads4.addLast(randomAdd);
                System.out.println("addLast(" + randomAdd + ")");
            }
            System.out.println("removeLast()");
            Integer expected = ads4.removeLast();
            Integer actual = sad4.removeLast();
            assertEquals("removeLast()", expected, actual);
            ads4.addFirst(expected);
            sad4.addFirst(actual);
            System.out.println("addFirst(" + expected + ")");
        }
        assertEquals(sad4.size(), ads4.size());
    }
}
