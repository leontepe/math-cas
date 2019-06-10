
package com.leontepe.expression;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

public class SummandsTest {
    
    // @Test
    // public void testSummandSplitting1() {
    //     Expression e1 = new Expression("3x^2 + 2x + 10");
    //     List<Expression> expressions1 = new ArrayList<Expression>();
    //     expressions1.add(new Expression("3x^2"));
    //     expressions1.add(new Expression("2x"));
    //     expressions1.add(new Expression("10"));
    //     assertEquals(e1.getSummands(), expressions1);
    // }

    // @Test
    // public void testSummandSplitting2() {
    //     Expression e1 = new Expression("3x^2-2x+10");
    //     List<Expression> expressions1 = new ArrayList<Expression>();
    //     expressions1.add(new Expression("3x^2"));
    //     expressions1.add(new Expression("-2x"));
    //     expressions1.add(new Expression("10"));
    //     List<Expression> summands = e1.getSummands();
    //     Expression ee1 = summands.get(1);
    //     Expression ee2 = expressions1.get(1);
    //     ee1.printElements();
    //     ee2.printElements();
    //     assertEquals(ee1, ee2);
    // }

    // @Test
    // public void testSummandSplitting3() {
    //     Expression e1 = new Expression("-2x^2 + 1/2*x - 10");
    //     List<Expression> expressions1 = new ArrayList<Expression>();
    //     expressions1.add(new Expression("-2x^2"));
    //     expressions1.add(new Expression("1/2*x"));
    //     expressions1.add(new Expression("-10"));
    //     assertEquals(e1.getSummands(), expressions1);
    // }

    // @Test
    // public void testSummandSplitting4() {
    //     Expression e1 = new Expression("+x^4-2.4x^3+10x-5");
    //     List<Expression> expressions1 = new ArrayList<Expression>();
    //     expressions1.add(new Expression("+x^4"));
    //     expressions1.add(new Expression("-2.4x^3"));
    //     expressions1.add(new Expression("10x"));
    //     expressions1.add(new Expression("-5"));
    //     assertEquals(e1.getSummands(), expressions1);
    // }

    // // Does this test belong here?
    // @Test
    // public void testImplicitMultiplication() {
    //     assertEquals(new Expression("3*x^2"), new Expression("3x^2"));
    //     assertEquals(new Expression("-2*x"), new Expression("-2x"));
    //     assertEquals(new Expression("10"), new Expression("10"));
    // }

}