
package com.leontepe.expression;

import java.util.List;
import java.util.ArrayList;

public class ExpressionTokenizer {

    /**
     * Tokenize expression string, i.e. convert it into a list of tokens (lexical analysis).
     */
    public static List<ExpressionElement> tokenize(String expressionString) {
        // Remove whitespace
        expressionString = expressionString.replaceAll("\\s+", "");

        // Initialize output list
        List<ExpressionElement> elements = new ArrayList<ExpressionElement>();
        
        // Declare variables
        int numberReadingStart = -1;

        // Iterate through string
        for(int i = 0; i < expressionString.length(); i++) {
            char c = expressionString.charAt(i);
            
            // Check if number reading should start
            if(numberReadingStart == -1 && Number.isNumberCharacter(c)) {
                    // Start reading in number
                    numberReadingStart = i;
            }
            else {
                // Check if number reading should end
                if(!Number.isNumberCharacter(c) && numberReadingStart != -1) {
                    String numberString = expressionString.substring(numberReadingStart, i);
                    elements.add(Number.get(numberString));
                    numberReadingStart = -1;
                }

                if(Operator.isOperator(c)) {

                    if(elements.isEmpty()) {
                        elements.add(Operator.get(c, Operator.Arity.UNARY));
                    }
                    else {
                        ExpressionElement previousToken = elements.get(elements.size()-1);
                        
                        if(previousToken instanceof Number || previousToken instanceof Variable || previousToken.equals(Parenthesis.RIGHT_PARENTHESIS)) {
                            elements.add(Operator.get(c, Operator.Arity.BINARY));
                        }
                        else if(previousToken instanceof Operator || previousToken.equals(Parenthesis.LEFT_PARENTHESIS)){
                            elements.add(Operator.get(c, Operator.Arity.UNARY));
                        }
                        else {
                            throw new RuntimeException("Tokenizer error");
                        }
                    }
                }
                else if(Parenthesis.isParenthesis(c)) {
                    elements.add(Parenthesis.get(c));
                }
                else if(Variable.isVariable(c)) {
                    elements.add(Variable.get(c));
                }
            }
        }

        // If number was still being read at the end, complete reading
        if(numberReadingStart != -1) {
            String numberString = expressionString.substring(numberReadingStart, expressionString.length());
            elements.add(Number.get(numberString));
            numberReadingStart = -1; 
        }

        return elements;
    }

}