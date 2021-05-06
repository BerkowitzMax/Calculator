import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    final Calculator c = new Calculator();

    @org.junit.jupiter.api.Test
    void sum() {
        System.out.println("Sum test");
        assertEquals(5, c.sum(2, 3));
    }
}