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
        System.out.println("T1_test passed.");

        // Test 2
        c = new Calculator();
        expected = "1*(2^3+4)";
        c.parseExpression("1*(2^3+4)$");
        test_output = stringify(c.stackGetter());
        assertEquals(expected, test_output);
        System.out.println("T2_test passed.");

        // Test 3
        c = new Calculator();
        expected = "1*(2^3+4)-(4%45/(22000.1))";
        c.parseExpression("1*(2^3+4)-(4%45/(22,000.1))$");
        test_output = stringify(c.stackGetter());
        assertEquals(expected, test_output);
        System.out.println("T3_test passed.");

        // Test 4
        c = new Calculator();
        expected = ".+.";
        c.parseExpression(".+.$");
        test_output = stringify(c.stackGetter());
        assertEquals(expected, test_output);
        System.out.println("T4_test passed.");
    }

    @org.junit.jupiter.api.Test
    void parseExpression_test_invalid() {
        Calculator c = new Calculator();

        // TEST 1
        assertThrows(NullPointerException.class, () -> {
            c.parseExpression("(1+1$");
        });
        System.out.println("T1_test_invalid passed.");

        // TEST 2
        assertThrows(NullPointerException.class, () -> {
            c.parseExpression("(1+1)*((1.)$");
        });
        System.out.println("T2_test_invalid passed.");

        // TEST 3
        assertThrows(NullPointerException.class, () -> {
            c.parseExpression("(1+1)*1..$");
        });
        System.out.println("T3_test_invalid passed.");

        // TEST 4
        assertThrows(NullPointerException.class, () -> {
            c.parseExpression("(1+1)*1.)$");
        });
        System.out.println("T4_test_invalid passed.");
    }

}