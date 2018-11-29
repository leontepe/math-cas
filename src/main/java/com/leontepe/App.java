package com.leontepe;

import java.util.ArrayList;
import java.util.List;


public class App 
{
    public static void main( String[] args )
    {
        Expression ex1 = new Expression("(-3.55 + 2.13) / 5481");
        Expression ex2 = new Expression("-512 * 32");
        List<ExpressionElement> elements = ex2.getExpressionElements();
        for(ExpressionElement el : elements) {
            System.out.println(el.getStringValue());
        }
    }

    public static double f(double x) {
        return -Math.pow(x, 2) - 7*x - 10;
    }

    public static double fPrime(double x) {
        return -2*x - 7;
    }

    public static List<Double> newton() {
        List<Double> roots = new ArrayList<Double>();
        double x = Math.random();
        for(int i = 0; i < 10; i++) {
            x = x - (f(x) / fPrime(x));
        }
        roots.add(x);
        return roots;
    }
}
