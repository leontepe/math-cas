
package com.leontepe.expression;

import java.util.List;

import com.leontepe.MathContext;
import com.leontepe.exception.TokenizationException;
import com.leontepe.function.Function;

import java.util.ArrayList;

public class ExpressionTokenizer {

    /**
     * Tokenize expression string, i.e. convert it into a list of tokens (lexical
     * analysis).
     */
    public static List<ExpressionElement> tokenize(MathContext context, String expressionString) {
        // Remove whitespace
        expressionString = expressionString.replaceAll("\\s+", "");

        // Initialize output list
        List<ExpressionElement> elements = new ArrayList<ExpressionElement>();

        // Declare variables
        int numberReadingStart = -1;
        int letterReadingStart = -1;

        // Iterate through string
        for (int i = 0; i < expressionString.length(); i++) {
            char c = expressionString.charAt(i);

            // Check if number reading should start
            if (numberReadingStart == -1 && Number.isNumberCharacter(c)) {
                // Start reading in number
                numberReadingStart = i;
            }
            else if (letterReadingStart == -1 && Character.isLetter(c)) {
                // Start reading in letters
                letterReadingStart = i;
            }
            else {
                // Check if number reading should end
                if (!Number.isNumberCharacter(c) && numberReadingStart != -1) {
                    String numberString = expressionString.substring(numberReadingStart, i);
                    elements.add(Number.get(numberString));
                    numberReadingStart = -1;
                }
                // Check if letter reading should end
                if(!Character.isLetter(c) && letterReadingStart != -1) {
                    String letterString = expressionString.substring(letterReadingStart, i);
                    if(context.containsFunction(letterString)) {
                        elements.add(context.getFunction(letterString));
                    }
                    else if(letterString.length() == 1 && context.containsVariable(letterString.charAt(0))) {
                        elements.add(context.getVariable(letterString.charAt(0)));
                    }
                    letterReadingStart = -1;
                }

                if (Operator.isOperator(c)) {
                    if (elements.isEmpty()) {
                        elements.add(Operator.get(c, Operator.Arity.UNARY));
                    } else {
                        ExpressionElement previousToken = elements.get(elements.size() - 1);

                        if (previousToken instanceof Number || previousToken instanceof Variable
                                || previousToken.equals(Parenthesis.RIGHT_PARENTHESIS)) {
                            elements.add(Operator.get(c, Operator.Arity.BINARY));
                        } else if (previousToken instanceof Operator
                                || previousToken.equals(Parenthesis.LEFT_PARENTHESIS)) {
                            elements.add(Operator.get(c, Operator.Arity.UNARY));
                        } else if (previousToken instanceof Function) {
                            throw new TokenizationException("Operator after function");
                        } else {
                            throw new RuntimeException("Unknown expression element type");
                        }
                    }
                } else if (Parenthesis.isParenthesis(c)) {
                    elements.add(Parenthesis.get(c));
                }
            }
        }

        // If number was still being read at the end, complete reading
        if (numberReadingStart != -1) {
            String numberString = expressionString.substring(numberReadingStart, expressionString.length());
            elements.add(Number.get(numberString));
            numberReadingStart = -1;
        }

        return elements;
    }

    public static List<ExpressionElement> tokenize(String expressionString) {
        MathContext context = new MathContext();
        return tokenize(context, expressionString);
    }

}