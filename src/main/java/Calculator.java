/*
* Max Berkowitz
*
* This is a practice project
* using Java. Calculator is ran
* in WSL, using Maven to manage
* build dependencies and JUnit
* for unit testing.
*/

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
        c.expression = reader.getStack();
        System.out.println(c.compute());
    }

    // evaluate cleaned expression
    public String compute() {
        // transfer from stack to vector
        Vector<String> exp = new Vector<>();
        while (!expression.empty()) {
            exp.add(expression.pop());
        }
        Collections.reverse(exp);

        // append 0 if empty decimal
        for (int i = 0; i < exp.size(); i++) {
            if (exp.get(i).equals(".")) {
                // decimal as the last character
                if (i >= exp.capacity()-1){
                    exp.add("0");
                } else {
                    // decimal not as the last character
                    try {
                        Integer.parseInt(String.valueOf(exp.get(i+1)));         // checking if operator or num
                    } catch (Exception e) {
                        exp.insertElementAt("0", i+1);
                    }
                }
            }
        }

        // JEval library for final string evaluation
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

