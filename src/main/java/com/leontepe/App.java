package com.leontepe;

import com.leontepe.expression.Expression;

import java.util.List;
import java.util.ArrayList;

public class App
{
    public static void main( String[] args )
    {
        // Expression e1 = new Expression("3x^2-2x+10");
        // Expression ee1 = e1.getSummands().get(1);
        // Expression ee2 = new Expression("-2x");
        // ee1.printElements();
        // ee2.printElements();
        // System.out.println(ee1.evaluate().getStringValue());

        // // Print is-elements
        // Expression e1 = new Expression("-2x^2 + 1/2*x - 10");
        // List<Expression> summands = e1.getSummands();
        // for(Expression e : summands) {
        //     e.printElements();
        // }
        // System.out.println();

        // // Print should-elements
        // List<Expression> expressions1 = new ArrayList<Expression>();
        // expressions1.add(new Expression("-2x^2"));
        // expressions1.add(new Expression("1/2*x"));
        // expressions1.add(new Expression("-10"));
        // for(Expression e : expressions1) {
        //     e.printElements();
        // }
        // System.out.println();

        // // Assure same list size
        // System.out.println(summands.size());
        // System.out.println(expressions1.size());

        // System.out.println(summands.get(0).getElements().size());

        // // Print element-wise equality
        // for(int i = 0; i < summands.size(); i++) {
        //     System.out.println(summands.get(i).equals(expressions1.get(i)));
        // }
    }

    // "-3+5" -> "0-3+5" -> "0", "-", "3", "+", "5" -> "03-5+" -> 2
    // "-2^3" -> "0-2^3" -> "0", "-", "2", "^", "3" -> "023^-" -> -8
    // "-(3+5)" -> "-(3+5)" -> "0", "-", "(", "3", "+", "5", ")" -> "035+-" -> -8
    // "-(x+3)"
    // => Solves unary operator problem
    // ==> But does cause other problems because

    // Two problems:
    
    // Should the expression {-}{x} equal {-x}?
    // E.g.: does [-][3] equal [-3]?
    // Answer (?): The evaluation result should be the same, but the expressions should not equal each other. So I need to manually enforce one of these to prevent them from causing problems.
}
