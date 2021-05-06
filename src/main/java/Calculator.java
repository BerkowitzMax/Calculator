import net.sourceforge.jeval.Evaluator;

import java.util.*;
import java.lang.*;

class parse_input {
    private final Set<String> allowed_operations = new HashSet<>
            (Arrays.asList("(",")","*","/","%","+","-", "."));
    Stack<String> exp = new Stack<>();

    parse_input(){}

    parse_input(String input) {
        if (input.equals("")) System.exit(0);

        // remove spaces, exit condition
        input = input.replaceAll("\\s", "");
        input += "$";
        this.parseExpression(input);
    }

    public Stack<String> stackGetter(){
        return exp;
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

            // throw error on back-to-back decimals
            if (c == '.') isValidDecimal();

            try {
                Integer.parseInt(String.valueOf(c));
                current += c;
            } catch (Exception e) {
                // push and clear current string value
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
                && !exp.peek().equals(")")
                && !exp.peek().equals("."))
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
}

public class Calculator {
    Stack<String> expression;

    public static void main(String[] args) {
        System.out.print("Input equation to evaluate: ");
        Scanner read = new Scanner(System.in);
        String input = read.nextLine();

        parse_input reader = new parse_input(input);

        Calculator c = new Calculator();
        c.expression = reader.stackGetter();

        System.out.println(c.compute());
    }

    public String compute() {
        // transfer to vector
        Vector<String> exp = new Vector<>();
        while (!expression.empty()) {
            exp.add(expression.pop());
        }
        Collections.reverse(exp);

        // format decimals
        for (int i = 0; i < exp.size(); i++) {
            if (exp.get(i).equals(".")) {
                // append 0 if empty decimal
                // IF END
                if (i >= exp.capacity()-1){
                    exp.add("0");
                }
                else {
                    // IN MIDDLE
                    try {
                        Integer.parseInt(String.valueOf(exp.get(i+1)));
                    } catch (Exception e) {
                        exp.insertElementAt("0", i+1);
                    }
                }
            }
        }

        String final_expression = String.join("", exp);
        System.out.println(final_expression);

        Evaluator result = new Evaluator();
        try {
            final_expression = result.evaluate(final_expression);
        } catch (Exception e) {return "Error: " + e.getMessage();}
        return final_expression;
    }
}
//Integer.parseInt(String.valueOf(item));

