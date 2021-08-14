import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void testIsNumber() {
        int a = 128;
        int b = 128;
        assertTrue("a and b should be equal", Flik.isSameNumber(a, b));
    }


}
