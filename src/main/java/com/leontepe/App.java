package com.leontepe;

import com.leontepe.expression.Expression;


public class App 
{
    public static void main( String[] args )
    {
        Expression ex = new Expression("3x^2-2x+10");
        ex.printElements();
        for(Expression e : ex.getSummands()) {
            e.printElements();
        }

        Expression ee1 = ex.getSummands().get(1);
        Expression ee2 = new Expression("-2x");
        ee1.printElements();
        ee2.printElements();
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
