import java.util.*;
import java.lang.*;

public class Calculator {
    private final Set<String> allowed_operations = new HashSet<>
            (Arrays.asList("(",")","^","*","/","%","+","-", "."));
    Stack<String> exp = new Stack<>();

    public final Stack<String> stackGetter(){
        return exp;
    }

    public static void main(String[] args) {
        System.out.print("Input equation to evaluate: ");
        Scanner read = new Scanner(System.in);
        String input = read.nextLine();

        if (input.equals("")) System.exit(0);

        // remove spaces, exit condition
        input = input.replaceAll("\\s", "");
        input += "$";

        Calculator c = new Calculator();
        c.parseExpression(input);
    }

    /* Back-to-back integers are combined
     * Spaces are ignored
     * Pushes complete strings to stack
     */
    public void parseExpression(String line) {
        String current = "";
        int paren = 0; // return error if not 0

        for (char c : line.toCharArray()) {
            if (c == ',') continue;

            if (c == '(') paren++;
            else if (c == ')') paren--;
            if (paren < 0) throw new NullPointerException("Invalid parenthesis order");

            if (c == '.') isValidDecimal();

            try {
                Integer.parseInt(String.valueOf(c));
                current += c;
            } catch (Exception e) {
                // push and clear current string
                if (!current.equals("")) exp.push(current);
                current = "";

                // pushes operators
                if (c != '$') {
                    if (!isValidOperation(Character.toString(c)))
                        throw new NullPointerException("Invalid operation");
                    exp.push(Character.toString(c));
                }
            }
        }

        if (paren != 0) throw new NullPointerException("Unbalanced parenthesis");
        if (allowed_operations.contains(exp.peek())
            && !exp.peek().equals(")"))
            throw new NullPointerException("Ends on operator");

        System.out.println(exp);
    }

    // exits upon: back to back operations
    //             invalid operator
    public boolean isValidOperation(String op) {
        if (op.equals("(") || op.equals(")")) return true;

        if (!exp.empty() && exp.peek().equals(op))
            throw new NullPointerException("Invalid input");
        return allowed_operations.contains(op);
    }

    public void isValidDecimal() {
        // invalid input
        if (!exp.empty() && exp.peek().equals("."))
            throw new NullPointerException("Invalid decimal");
    }

    public int sum(int a, int b) {
        return a + b;
    }
}
