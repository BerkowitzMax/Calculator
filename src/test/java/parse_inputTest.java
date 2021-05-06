import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class parse_inputTest {

    private final String stringify(Stack<String> input) {
        String out = "";
        for (String str : input) {
            out += str;
        }
        return out;
    }

    @Test
    void parseExpression_test() {
        String test_output = "yeet";
        String expected = "yeet";
        parse_input c = new parse_input();

        // TEST 1
        expected = "1+1";
        c = new parse_input("1+1$");
        test_output = stringify(c.stackGetter());
        assertEquals(expected, test_output);
        System.out.println("T1_test passed.");

        // Test 2
        expected = "1*(2^3+4)";
        c = new parse_input("1*(2^3+4)$");
        test_output = stringify(c.stackGetter());
        assertEquals(expected, test_output);
        System.out.println("T2_test passed.");

        // Test 3
        expected = "1*(2^3+4)-(4%45/(22000.1))";
        c = new parse_input("1*(2^3+4)-(4%45/(22,000.1))$");
        test_output = stringify(c.stackGetter());
        assertEquals(expected, test_output);
        System.out.println("T3_test passed.");

        // Test 4
        expected = ".+.";
        c = new parse_input(".+.$");
        test_output = stringify(c.stackGetter());
        assertEquals(expected, test_output);
        System.out.println("T4_test passed.");
    }

    @Test
    void parseExpression_test_invalid() {
        // TEST 1
        assertThrows(NullPointerException.class, () -> {
            new parse_input("(1+1$");
        });
        System.out.println("T1_test_invalid passed.");

        // TEST 2
        assertThrows(NullPointerException.class, () -> {
            new parse_input("(1+1)*((1.)$");
        });
        System.out.println("T2_test_invalid passed.");

        // TEST 3
        assertThrows(NullPointerException.class, () -> {
            new parse_input("(1+1)*1..$");
        });
        System.out.println("T3_test_invalid passed.");

        // TEST 4
        assertThrows(NullPointerException.class, () -> {
            new parse_input("(1+1)*1.)$");
        });
        System.out.println("T4_test_invalid passed.");
    }
}