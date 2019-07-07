
package com.leontepe.expression;

import java.util.List;

import com.leontepe.MathContext;
import com.leontepe.exception.TokenizationException;
import com.leontepe.expression.Operator.NotationType;
import com.leontepe.function.Function;
import com.leontepe.exception.AmbiguousSyntaxException;
import com.leontepe.exception.InvalidSyntaxException;
import com.leontepe.exception.NotImplementedException;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ExpressionTokenizer {

    /**
     * Tokenize expression string, i.e. convert it into a list of tokens (lexical
     * analysis).
     */
    public static List<ExpressionElement> tokenize(String expressionString, MathContext context)
            throws InvalidSyntaxException, NotImplementedException, AmbiguousSyntaxException {

        checkParentheses(expressionString);

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

                    if (context.isFunctionDeclared(letterString)) {
                        elements.add(context.getDeclaredFunction(letterString));
                    }
                    else if (letterString.length() == 1) {
                        if (i + 2 < expressionString.length()) {
                            char c1 = expressionString.charAt(i);
                            char c2 = expressionString.charAt(i + 1);
                            char c3 = expressionString.charAt(i + 2);
                            if (c1 == Parenthesis.LEFT_PARENTHESIS.getChar()
                                && Character.isLetter(c2)
                                && c3 == Parenthesis.RIGHT_PARENTHESIS.getChar()) {
                                if (!context.isVariableDeclared(c2)) {
                                    Function f = new Function(letterString, null, 1);
                                    context.declareFunction(f);
                                    elements.add(f);
                                }
                                else {
                                    throw new AmbiguousSyntaxException(String.format("Letter %c at [%d] is already declared as a variable", c2, (i+1+1)));
                                }
                            }
                        }
                        else {
                            char varChar = letterString.charAt(0);
                            if (context.isVariableDeclared(varChar)) {
                                elements.add(context.getDeclaredVariable(varChar));
                            }
                            else {
                                Variable var = new Variable(varChar);
                                context.declareVariable(var);
                                elements.add(var);
                            }
                        }
                    }
                    else {
                        throw new NotImplementedException("Undefinded Multicharacter function");
                    }

                    letterReadingStart = -1;
                }

                if (Character.isLetter(c) || Number.isNumberCharacter(c)) {
                    continue;
                }
                else if (Operator.isOperatorChar(c)) {
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
                            throw new InvalidSyntaxException("Token before operator of unexpected type");
                        }
                    }
                }
                else if (Parenthesis.isParenthesisChar(c)) {
                    elements.add(Parenthesis.getParenthesis(c));
                }
                else if (Separator.isSeparatorChar(c)) {
                    elements.add(Separator.getSeparator(c));
                }
                else {
                    throw new InvalidSyntaxException("Unexpected character");
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
            if (context.isFunctionDeclared(letterString)) {
                elements.add(context.getDeclaredFunction(letterString));
            }
            else if (letterString.length() == 1) {
                char varChar = letterString.charAt(0);
                if (context.isVariableDeclared(varChar)) {
                    elements.add(context.getDeclaredVariable(varChar));
                }
                else {
                    Variable var = new Variable(varChar);
                    elements.add(var);
                    context.declareVariable(var);
                }
            }
            letterReadingStart = -1;
        }

        // for (int i = 0; i < elements.size(); i++) {
        //     ExpressionElement el1 = elements.get(i);
        //     if (el1 instanceof Function && !(el1 instanceof Operator)
        //         && i + 3 < elements.size()) {
        //         ExpressionElement el2 = elements.get(i + 1);
        //         ExpressionElement el3 = elements.get(i + 2);
        //         ExpressionElement el4 = elements.get(i + 3);
        //         if (el2 == Parenthesis.LEFT_PARENTHESIS
        //             && el3 instanceof Variable && !((Variable)el3).hasValue()
        //             && el4 == Parenthesis.RIGHT_PARENTHESIS) {
        //                 context.declareFunction(f);
        //             }
        //     }
        // }

        return elements;
    }

    public static List<ExpressionElement> tokenize(String expressionString) throws InvalidSyntaxException {
        MathContext context = new MathContext();
        return tokenize(expressionString, context);
    }

    private static void checkParentheses(String expressionString) throws InvalidSyntaxException {
        long leftParenCount = expressionString.chars().filter(ch -> ch == Parenthesis.LEFT_PARENTHESIS.getChar())
                .count();
        long rightParenCount = expressionString.chars().filter(ch -> ch == Parenthesis.RIGHT_PARENTHESIS.getChar())
                .count();
        if (leftParenCount != rightParenCount) {
            throw new InvalidSyntaxException("Mismatched parentheses");
        }
    }

}