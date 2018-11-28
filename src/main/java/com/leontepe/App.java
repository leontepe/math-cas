package com.leontepe;

import java.util.ArrayList;
import java.util.List;


public class App 
{
    public static void main( String[] args )
    {
        int i = 12;
        boolean x = (i == 12);
        while(x) {
            i = 15;
            System.out.println("No!!!");
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
