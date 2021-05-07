import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class parseInputTest {

    private final String stringify(Stack<String> input) {
        String out = "";
        for (String str : input) {
            out += str;
        }
        return out;
    }

    @Test
    void parseExpressionBasic() {
        String expected = "1+1";
        parseInput c = new parseInput("1+1");
        String testOutput = stringify(c.stackGetter());

        assertEquals(expected, testOutput);
    }

    @Test
    void parseExpressionOperators() {
        String expected = "1*(3+4)";
        parseInput c = new parseInput("1*(3+4)");
        String testOutput = stringify(c.stackGetter());

        assertEquals(expected, testOutput);

        // Testing symbols and spaces
        expected = "1*2";
        c = new parseInput("1$$*    2");
        testOutput = stringify(c.stackGetter());

        assertEquals(expected, testOutput);
    }

    @Test
    void parseExpressionComplex() {
        String expected = "1*(2+4)-(4%45/(22000.1))";
        parseInput c = new parseInput("1*(2+4)-(4%45/(22,000.1))");
        String testOutput = stringify(c.stackGetter());

        assertEquals(expected, testOutput);
    }

    @Test
    void parseExpressionDecimal() {
        String expected = ".+.";
        parseInput c = new parseInput(".+.");
        String testOutput = stringify(c.stackGetter());

        assertEquals(expected, testOutput);
    }

    @Test
    void parseInvalidExpressionParenthesis() {
        assertThrows(NullPointerException.class, () -> {
            new parseInput("(1+1");
        });

        assertThrows(NullPointerException.class, () -> {
            new parseInput("(1+1)*((1.)");
        });

        assertThrows(NullPointerException.class, () -> {
            new parseInput("(1+1)*1.)");
        });

        assertThrows(NullPointerException.class, () -> {
            new parseInput("1 + )( 2");
        });
    }

    @Test
    void parseInvalidExpressionDecimal() {
        assertThrows(NullPointerException.class, () -> {
            new parseInput("(1+1)*1..");
        });

        assertThrows(NullPointerException.class, () -> {
            new parseInput("1.0 + 2.. *3");
        });
    }

    @Test
    void parseInvalidExpressionOperators() {
        assertThrows(NullPointerException.class, () -> {
            new parseInput("(1+1)*1+");
        });

        assertThrows(NullPointerException.class, () -> {
            new parseInput("(1+1)**1");
        });

        assertThrows(NullPointerException.class, () -> {
            new parseInput("//(1+1)*1");
        });
    }
}