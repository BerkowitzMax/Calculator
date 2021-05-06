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

    @org.junit.jupiter.api.Test
    void parseExpression_test() {
        String test_output = "yeet";
        String expected = "yeet";
        Calculator c = new Calculator();

        // TEST 1
        expected = "1+1";
        c.parseExpression("1+1$");
        test_output = stringify(c.stackGetter());
        assertEquals(expected, test_output);

        // Test 2
        c = new Calculator();
        expected = "1*(2^3+4)";
        c.parseExpression("1*(2^3+4)$");
        test_output = stringify(c.stackGetter());
        assertEquals(expected, test_output);

        // Test 3
        c = new Calculator();
        expected = "1*(2^3+4)-(4%45/(22000.1))";
        c.parseExpression("1*(2^3+4)-(4%45/(22,000.1))$");
        test_output = stringify(c.stackGetter());
        assertEquals(expected, test_output);
    }

}