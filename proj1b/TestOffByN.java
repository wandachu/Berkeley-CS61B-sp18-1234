import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offByN = new OffByN(5);

    @Test
    public void testEqualChars() {
        assertTrue(offByN.equalChars('a', 'f'));
        assertTrue(offByN.equalChars('f', 'a'));
        assertTrue(offByN.equalChars('z', 'u'));
        assertFalse(offByN.equalChars('f', 'h'));
        assertFalse(offByN.equalChars('a', 'F'));
        assertFalse(offByN.equalChars('a', 'e'));
        assertFalse(offByN.equalChars('b', 'a'));
    }
}
