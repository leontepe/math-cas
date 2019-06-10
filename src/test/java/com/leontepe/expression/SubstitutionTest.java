
package com.leontepe.expression;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.leontepe.expression.Number;

public class SubstitutionTest {

    // @Test
    // public void testSubstitution() {
    //     Expression e1 = new Expression("3x - 5");
    //     List<ExpressionElement> elements1 = new ArrayList<ExpressionElement>();
    //     elements1.add(Number.get("3"));
    //     elements1.add(Operator.get("*"));
    //     elements1.add(Number.get("2"));
    //     elements1.add(Operator.get("-"));
    //     elements1.add(Number.get("5"));
    //     assertEquals(e1.substitute(Variable.get("x"), new Number(2)).getElements(), elements1);

    //     Expression e2 = new Expression("1/4x^2 - 2x + 3");
    //     List<ExpressionElement> elements2 = new ArrayList<ExpressionElement>();
    //     elements2.add(Number.get("1"));
    //     elements2.add(Operator.get("/"));
    //     elements2.add(Number.get("4"));
    //     elements2.add(Operator.get("*"));
    //     elements2.add(Number.get("7"));
    //     elements2.add(Operator.get("^"));
    //     elements2.add(Number.get("2"));
    //     elements2.add(Operator.get("-"));
    //     elements2.add(Number.get("2"));
    //     elements2.add(Operator.get("*"));
    //     elements2.add(Number.get("7"));
    //     elements2.add(Operator.get("+"));
    //     elements2.add(Number.get("3"));
    //     assertEquals(e2.substitute(Variable.get("x"), new Number(7)).getElements(), elements2);
    // }

}