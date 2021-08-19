import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void testArrayDeque() {
        //Test addFirst()
        System.out.println("Testing on addFirst...");
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

        assertTrue(ads.isEmpty());
        assertTrue(sad.isEmpty());

        String output = "";

        int count = 0;
        while (count < 10000) {
            double random = StdRandom.uniform();

            if (random < 0.25) { //test addFirst
                ads.addFirst(count);
                sad.addFirst(count);
                Integer actual = sad.get(0);
                Integer expected = ads.get(0);
                output += "\naddFirst(" + count + ")";
                assertEquals(output, expected, actual);
            } else if (random < 0.5) { //test addlast
                ads.addLast(count);
                sad.addLast(count);
                Integer actual = sad.get(sad.size() - 1);
                Integer expected = ads.get(ads.size() - 1);
                output += "\naddLast(" + count + ")";
                assertEquals(output, expected, actual);
            } else if (random < 0.75) { //test removeFirst
                if (ads.isEmpty()) {
                    continue;
                }
                Integer expected = ads.removeFirst();
                Integer actual = sad.removeFirst();
                output += "\nremoveFirst()";
                assertEquals(output, expected, actual);
            } else { //test removeLast
                if (ads.isEmpty()) {
                    continue;
                }
                Integer expected = ads.removeLast();
                Integer actual = sad.removeLast();
                output += "\nremoveLast()";
                assertEquals(output, expected, actual);
            }
            count++;
        }
    }
}