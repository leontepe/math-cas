package com.leontepe;

import com.leontepe.expression.*;
import com.leontepe.expression.Number;
import com.leontepe.function.*;


public class App 
{
    public static void main( String[] args )
    {
        Function f3 = new Function("z(x, y) = - 7 x + 2y - 10x + 0");
        f3.getExpression().printElements();
        System.out.println();
        new Expression("-7x + 2y - 10x + 0").printElements();
    }

    // "-3+5" -> "0-3+5" -> "0", "-", "3", "+", "5" -> "03-5+" -> 2
    // "-2^3" -> "0-2^3" -> "0", "-", "2", "^", "3" -> "023^-" -> -8
    // "-(3+5)" -> "0-(3+5)" -> "0", "-", "(", "3", "+", "5", ")" -> "035+-" -> -8
    // ==> Solves unary operator problem and power problem
}
