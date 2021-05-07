import net.sourceforge.jeval.Evaluator;

import java.util.*;
import java.lang.*;

public class Calculator {
    Stack<String> expression;

    public static void main(String[] args) {
        System.out.print("Input equation to evaluate: ");
        Scanner read = new Scanner(System.in);
        String input = read.nextLine();

        parseInput reader = new parseInput(input);

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
                } else {
                    // IN MIDDLE
                    try {
                        Integer.parseInt(String.valueOf(exp.get(i+1)));
                    } catch (Exception e) {
                        exp.insertElementAt("0", i+1);
                    }
                }
            }
        }

        String finalExpression = String.join("", exp);
        System.out.println(finalExpression);

        Evaluator result = new Evaluator();
        try {
            return result.evaluate(finalExpression);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

