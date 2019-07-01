
package com.leontepe.expression;

import java.util.List;

import com.leontepe.MathContext;
import com.leontepe.exception.TokenizationException;
import com.leontepe.expression.Operator.NotationType;
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
                if (numberReadingStart != -1 && !Number.isNumberCharacter(c)) {
                    String numberString = expressionString.substring(numberReadingStart, i);
                    elements.add(Number.get(numberString));
                    numberReadingStart = -1;
                }
                // Check if letter reading should end
                if (letterReadingStart != -1 && !Character.isLetter(c)) {
                    String letterString = expressionString.substring(letterReadingStart, i);
                    if (context.containsFunction(letterString)) {
                        elements.add(context.getFunction(letterString));
                    }
                    else if (letterString.length() == 1) {
                        char varChar = letterString.charAt(0);
                        if (context.containsVariable(varChar)) {
                            elements.add(context.getVariable(varChar));
                        }
                        else {
                            Variable var = new Variable(varChar);
                            elements.add(var);
                            context.addVariable(var);
                        }
                    }
                    letterReadingStart = -1;
                }

                if (Operator.isOperatorChar(c)) {
                    if (elements.isEmpty()) {
                        elements.add(Operator.getOperator(c, 1, NotationType.PREFIX));
                    }
                    else {
                        ExpressionElement previousToken = elements.get(elements.size() - 1);

                        if (previousToken instanceof Number || previousToken instanceof Variable
                                || previousToken == Parenthesis.RIGHT_PARENTHESIS || (previousToken instanceof Operator
                                        && ((Operator) previousToken).getNotationType() == NotationType.POSTFIX)) {
                            Operator binaryOp = Operator.getOperator(c, 2, NotationType.INFIX);
                            Operator unaryPostfixOp = Operator.getOperator(c, 1, NotationType.POSTFIX);
                            elements.add(binaryOp != null ? binaryOp : unaryPostfixOp);
                        }
                        else if (previousToken instanceof Operator || previousToken == Parenthesis.LEFT_PARENTHESIS) {
                            elements.add(Operator.getOperator(c, 1, NotationType.PREFIX));
                        }
                        else {
                            // syntax error
                        }
                    }
                }
                else if (Parenthesis.isParenthesisChar(c)) {
                    elements.add(Parenthesis.getParenthesis(c));
                }
                else if (Separator.isSeparatorChar(c)) {
                    elements.add(Separator.getSeparator(c));
                }
            }
        }

        // If number was still being read at the end, complete reading
        if (numberReadingStart != -1) {
            String numberString = expressionString.substring(numberReadingStart, expressionString.length());
            elements.add(Number.get(numberString));
            numberReadingStart = -1;
        }
        else if (letterReadingStart != -1) {
            String letterString = expressionString.substring(letterReadingStart, expressionString.length());
            if (context.containsFunction(letterString)) {
                elements.add(context.getFunction(letterString));
            }
            else if (letterString.length() == 1) {
                char varChar = letterString.charAt(0);
                if (context.containsVariable(varChar)) {
                    elements.add(context.getVariable(varChar));
                }
                else {
                    Variable var = new Variable(varChar);
                    elements.add(var);
                    context.addVariable(var);
                }
            }
            letterReadingStart = -1;
        }

        return elements;
    }

    public static List<ExpressionElement> tokenize(String expressionString) {
        MathContext context = new MathContext();
        return tokenize(context, expressionString);
    }

}