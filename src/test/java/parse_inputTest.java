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
    void parseExpression_basic() {
        String expected = "1+1";
        parse_input c = new parse_input("1+1");
        String test_output = stringify(c.stackGetter());

        assertEquals(expected, test_output);
    }

    @Test
    void parseExpression_operators() {
        String expected = "1*(3+4)";
        parse_input c = new parse_input("1*(3+4)");
        String test_output = stringify(c.stackGetter());

        assertEquals(expected, test_output);

        // Testing symbols and spaces
        expected = "1*2";
        c = new parse_input("1$$*    2");
        test_output = stringify(c.stackGetter());

        assertEquals(expected, test_output);
    }

    @Test
    void parseExpression_complex() {
        String expected = "1*(2+4)-(4%45/(22000.1))";
        parse_input c = new parse_input("1*(2+4)-(4%45/(22,000.1))");
        String test_output = stringify(c.stackGetter());

        assertEquals(expected, test_output);
    }

    @Test
    void parseExpression_decimal() {
        String expected = ".+.";
        parse_input c = new parse_input(".+.");
        String test_output = stringify(c.stackGetter());

        assertEquals(expected, test_output);
    }

    @Test
    void parseInvalidExpression_parenthesis() {
        assertThrows(NullPointerException.class, () -> {
            new parse_input("(1+1");
        });

        assertThrows(NullPointerException.class, () -> {
            new parse_input("(1+1)*((1.)");
        });

        assertThrows(NullPointerException.class, () -> {
            new parse_input("(1+1)*1.)");
        });

        assertThrows(NullPointerException.class, () -> {
            new parse_input("1 + )( 2");
        });
    }

    @Test
    void parseInvalidExpression_decimal() {
        assertThrows(NullPointerException.class, () -> {
            new parse_input("(1+1)*1..");
        });

        assertThrows(NullPointerException.class, () -> {
            new parse_input("1.0 + 2.. *3");
        });
    }

    @Test
    void parseInvalidExpression_operators() {
        assertThrows(NullPointerException.class, () -> {
            new parse_input("(1+1)*1+");
        });

        assertThrows(NullPointerException.class, () -> {
            new parse_input("(1+1)**1");
        });

        assertThrows(NullPointerException.class, () -> {
            new parse_input("//(1+1)*1");
        });
    }
}