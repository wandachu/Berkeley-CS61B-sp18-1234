package byog.lab5;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestHexWorld {

    @Test
    public void testGetElement() {
        HexWorld hw1 = new HexWorld();
        int side = 2;
        int[] actual = new int[side * 2];
        for (int i = 1; i <= 2 * side; i++) {
            actual[i - 1] = hw1.getNumElement(i, side);
        }
        int[] expected = {2, 4, 4, 2};
        assertArrayEquals(expected, actual);

        side = 4;
        actual = new int[side * 2];
        for (int i = 1; i <= 2 * side; i++) {
            actual[i - 1] = hw1.getNumElement(i, side);
        }
        int[] expected2 = {4, 6, 8, 10, 10, 8, 6, 4};
        assertArrayEquals(expected2, actual);
    }

    @Test
    public void testGetSpace() {
        HexWorld hw1 = new HexWorld();
        int side = 2;
        int[] actual = new int[side * 2];
        for (int i = 1; i <= 2 * side; i++) {
            actual[i - 1] = hw1.getNumSpacePerSide(i, side);
        }
        int[] expected = {1, 0, 0, 1};
        assertArrayEquals(expected, actual);

        side = 4;
        actual = new int[side * 2];
        for (int i = 1; i <= 2 * side; i++) {
            actual[i - 1] = hw1.getNumSpacePerSide(i, side);
        }
        int[] expected2 = {3, 2, 1, 0, 0, 1, 2, 3};
        assertArrayEquals(expected2, actual);
    }

}
