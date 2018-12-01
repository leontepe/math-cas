package com.leontepe;

import com.leontepe.expression.*;


public class App 
{
    public static void main( String[] args )
    {
        Expression ex2 = new Expression("-3+5");
        for(ExpressionElement el : ex2.getPostfix()) {
            System.out.println(el.getStringValue());
        }
    }

    // "-3+5" -> "0-3+5" -> "0", "-", "3", "+", "5" -> "03-5+" -> 2
    // "-2^3" -> "0-2^3" -> "0", "-", "2", "^", "3" -> "023^-" -> -8
    // "-(3+5)" -> "0-(3+5)" -> "0", "-", "(", "3", "+", "5", ")" -> "035+-" -> -8
    // ==> Solves unary operator problem and power problem
}
