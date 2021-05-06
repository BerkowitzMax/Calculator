import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class CalculatorTest {
    private final String stringify(Stack<String> input) {
        String out = "";
        for (String str : input) {
            out += str;
        }
        return out;
    }

    @org.junit.jupiter.api.Test
    void sum_test() {
        final Calculator c = new Calculator();
        assertEquals(5, c.sum(2, 3));
    }
}