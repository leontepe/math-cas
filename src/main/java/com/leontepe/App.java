package com.leontepe;

import com.leontepe.expression.Expression;
import com.leontepe.expression.Operator;

import java.util.List;
import java.util.ArrayList;

import com.leontepe.expression.ExpressionElement;
import com.leontepe.expression.ExpressionTokenizer;

public class App
{
    public static void main( String[] args )
    {
        // new Expression("4+3*2").getSyntaxTree().print();
        // new Expression("4-2+3-1").getSyntaxTree().print();


        Expression ex1 = new Expression("a---b");
        ex1.getSyntaxTree().print();

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
}
