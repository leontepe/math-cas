package com.leontepe;

import java.util.ArrayList;
import java.util.List;


public class App 
{
    public static void main( String[] args )
    {
        // List<Double> roots = newton();
        // roots.forEach(root -> System.out.println(root));

        Expression ex = new Expression("7/8 - 4*3");
        System.out.println(ex.getPostfix());
        
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
