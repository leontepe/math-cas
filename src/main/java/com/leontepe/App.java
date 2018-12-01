package com.leontepe;

import com.leontepe.expression.*;
import com.leontepe.expression.Number;


public class App 
{
    public static void main( String[] args )
    {
        Number result = new Expression("2 + 8/4").evaluate();
        System.out.println(result.isInteger());
    }
}
