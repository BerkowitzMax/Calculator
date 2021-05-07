import java.util.*;

class parseInput {
    private final Set<String> allowedOperations = new HashSet<>
            (Arrays.asList("(", ")", "*", "/", "%", "+", "-", "."));
    Stack<String> exp = new Stack<>();

    parseInput(String input) {
        if (input.equals("")) System.exit(0);

        // remove spaces, exit condition
        input = input.replaceAll("\\s", "");
        input += "$";
        this.parseExpression(input);
    }

    public Stack<String> getStack() {
        return this.exp;
    }

    /*
     * back-to-back integers are combined
     * spaces and commas are ignored
     */
    public void parseExpression(String line) {
        StringBuilder stringInt = new StringBuilder();
        int paren = 0;                                      // track unbalanced parenthesis

        for (char c : line.toCharArray()) {
            if (c == ',') continue;

            if (c == '(') paren++;
            else if (c == ')') paren--;
            if (paren < 0) throw new RuntimeException("Invalid parenthesis order");

            // throw error on back-to-back decimals
            if (c == '.') isValidDecimal();

            try {
                Integer.parseInt(String.valueOf(c));
                stringInt.append(c);
            } catch (Exception e) {
                // push and clear stringInt string value
                if (!stringInt.toString().equals("")) exp.push(stringInt.toString());
                stringInt = new StringBuilder();

                // push operator
                if (c != '$') {
                    if (!isValidOperation(Character.toString(c)))
                        throw new RuntimeException("Invalid operation");
                    exp.push(Character.toString(c));
                }
            }
        }

        if (paren != 0) throw new RuntimeException("Unbalanced parenthesis");
        if (allowedOperations.contains(exp.peek())
                && !exp.peek().equals(")")
                && !exp.peek().equals(".")) {
            throw new RuntimeException("Ends on operator");
        }

        System.out.println(exp);
    }

    // exits upon: back to back operators
    //             invalid operators
    public boolean isValidOperation(String op) {
        if (op.equals("(") || op.equals(")"))
            return true;

        if (!exp.empty() && exp.peek().equals(op))
            throw new RuntimeException("Invalid input");
        return allowedOperations.contains(op);
    }

    // exits upon: back-to-back decimals
    public void isValidDecimal() {
        if (!exp.empty() && exp.peek().equals("."))
            throw new RuntimeException("Invalid decimal");
    }
}
