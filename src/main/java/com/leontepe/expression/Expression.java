
package com.leontepe.expression;

import java.util.*;

import com.leontepe.expression.Operator.Associativity;
import com.leontepe.syntaxtree.Node;
import com.leontepe.NotationConverter;
import com.leontepe.exception.EvaluationException;
import com.leontepe.exception.ExpressionParsingException;

public class Expression extends ExpressionElement {
    
    private Node<ExpressionElement> syntaxTree;

    public Expression(String expressionString) {
        List<ExpressionElement> infix = parseElementList(expressionString);
        List<ExpressionElement> postfix = NotationConverter.infixToPostfix(infix);
        this.syntaxTree = constructSyntaxTree(postfix);
    }

    public Expression(List<ExpressionElement> infix) {
        List<ExpressionElement> postfix = NotationConverter.infixToPostfix(infix);
        this.syntaxTree = constructSyntaxTree(postfix);
    }
    
    public Node<ExpressionElement> getSyntaxTree() { return this.syntaxTree; }
    
    public String getStringValue() {
        String s = "";
        // for(ExpressionElement el : elements) {
        //     s += el.getStringValue();
        // }
        return s;
    }

    private static List<ExpressionElement> parseElementList(String s) {
        s = s.replaceAll("\\s+", "");
        List<ExpressionElement> elements = new ArrayList<ExpressionElement>();
        int numberStartIndex = -1;

        for(int i = 0; i < s.length(); i++) {
            String c = s.substring(i, i+1);

            // if(isSignCharacter(s, i)) {
            //     elements.add(new Number(0));
            // }
            
            if(numberStartIndex == -1 && Number.isNumberCharacter(c)) {
                    // start reading number
                    numberStartIndex = i;
            }
            else {
                if(!Number.isNumberCharacter(c) && numberStartIndex != -1) {
                    String numberString = s.substring(numberStartIndex, i);
                    elements.add(Number.get(numberString));
                    numberStartIndex = -1;
                }
                if(Operator.isOperator(c)) {
                    elements.add(Operator.get(c));
                }
                else if(Paranthesis.isParanthesis(c)) {
                    elements.add(Paranthesis.get(c));
                }
                else if(Variable.isVariable(c)) {
                    if(i > 0) {
                        String previous = s.substring(i-1, i);
                        if(Number.isNumberCharacter(previous) || Paranthesis.isRightParanthesis(previous)) {
                            elements.add(Operator.get("*"));
                        }
                    }
                    elements.add(Variable.get(c));
                }
            }
        }
        if(numberStartIndex != -1) {
            String numberString = s.substring(numberStartIndex, s.length());
            elements.add(Number.get(numberString));
            numberStartIndex = -1; 
        }

        return elements;
    }

    /**
     * Constructs a syntax tree from a postfix token list.
     */
    private static Node<ExpressionElement> constructSyntaxTree(List<ExpressionElement> postfix) {

        Stack<Node<ExpressionElement>> nodeStack = new Stack<Node<ExpressionElement>>();
        for(ExpressionElement el : postfix) {
            if(el instanceof Number) {
                Number number = (Number)el;
                Node<ExpressionElement> numberNode = new Node<ExpressionElement>(number);
                nodeStack.push(numberNode);
            }
            else if(el instanceof Variable) {
                Variable variable = (Variable)el;
                Node<ExpressionElement> variableNode = new Node<ExpressionElement>(variable);
                nodeStack.push(variableNode);
            }
            else if(el instanceof Operator) {
                Operator operator = (Operator)el;
                Node<ExpressionElement> operatorNode = new Node<ExpressionElement>(operator);
                Node<ExpressionElement> operandNode2 = nodeStack.pop();
                Node<ExpressionElement> operandNode1 = nodeStack.pop();
                operatorNode.addChild(operandNode1);
                operatorNode.addChild(operandNode2);
                nodeStack.push(operatorNode);
            }
            else throw new RuntimeException("Token type not caught.");
        }

        if(nodeStack.size() > 1) {
            throw new RuntimeException("More than one item left in node stack.");
        }

        return nodeStack.pop();
    }

    // private void checkExpression() {
    //     if(elements.size() == 0) throw new ExpressionParsingException("Empty expression.");
    //     if(elements.size() == 1 && !(elements.get(0) instanceof Number) && !(elements.get(0) instanceof Variable))
    //         throw new ExpressionParsingException("Only numbers and variables can stand alone in an expression.");
    //     checkParantheses();
    // }

    // private void checkParantheses() {
    //     int parenCount = 0;
    //     for(ExpressionElement el : elements) {
    //         if(el.equals(Paranthesis.LEFT_PARANTHESIS)) {
    //             parenCount++;
    //         }
    //         else if(el.equals(Paranthesis.RIGHT_PARANTHESIS)) {
    //             parenCount--;
    //         }
    //         if(parenCount < 0) {
    //             throw new ExpressionParsingException("Mismatched parantheses.");
    //         }
    //     }
    //     if(parenCount != 0) {
    //         throw new ExpressionParsingException("Mismatched parantheses.");
    //     }
    // }

    /**
     * Determines if a plus/minus character in an expression string is a sign or an operator.
     */
    private static boolean isSignCharacter(String s, int i) {
        return "+-".contains(s.substring(i, i+1)) &&
        (i == 0 || s.substring(i-1, i).equals(Paranthesis.LEFT_PARANTHESIS.getStringValue()));
    }
    
    // "-3+5" -> [-3][+][5]



    // public Number evaluate() {

    //     // cannot evaluate expressions with variables
    //     if(getVariables().size() > 0) {
    //         throw new EvaluationException();
    //     }

    //     Stack<Number> numberStack = new Stack<Number>();
    //     List<ExpressionElement> postfix = getPostfix();
    //     for(ExpressionElement el : postfix) {
    //         if(el instanceof Number) {
    //             Number number = (Number)el;
    //             numberStack.push(number);
    //         }
    //         else if(el instanceof Operator) {
    //             Operator operator = (Operator)el;
    //             Number operand2 = numberStack.pop();
    //             Number operand1 = numberStack.pop();
    //             numberStack.push(operator.operate(operand1, operand2));
    //         }
    //         else throw new EvaluationException();
    //     }
    //     return numberStack.firstElement();
    // }

    // public List<Variable> getVariables() {
    //     List<Variable> variables = new ArrayList<Variable>();
    //     for(ExpressionElement el : elements) {
    //         if(el instanceof Variable) {
    //             variables.add((Variable)el);
    //         }
    //     }
    //     return variables;
    // }

    // public void printElements() {
    //     int i = 0;
    //     for(ExpressionElement el : elements) {
    //         System.out.print("{" + el.getStringValue() + "}");
    //     }
    //     System.out.println();
    // }

    // @Override
    // public boolean equals(Object obj) {
    //     if(obj instanceof Expression) {
    //         Expression ex = (Expression)obj;
    //         return ex.getElements().equals(this.elements);
    //     }
    //     return false;
    // }

    // public Expression substitute(Variable variable, Number number) {
    //     for(ExpressionElement el : elements) {
    //         if(el instanceof Variable) {
    //             Variable var = (Variable)el;
    //             if(variable.equals(var)) {
    //                 int i = elements.indexOf(el);
    //                 elements.set(i, number);
    //             }
    //         }
    //     }
    //     return new Expression(elements);
    // }

    // List<Expression> or List<ExpressionElement>?

    // Expression("3x^4-5x^2+2x-4").getSummands()
    // => Expression[]: 3x^4, -5x^2, 2x, -4
    // Expression("x^3 - (3+4)x - 4 / (3 / 2)").getSummands()
    // => Expression[]: x^3, -(3+4)x, -4 / (3/2)

    // public List<Expression> getSummands() {
    //     Operator plus = Operator.get("+");
    //     Operator minus = Operator.get("-");
    //     List<Expression> summands = new ArrayList<Expression>();
    //     int start = 0;
    //     int end = 0;
    //     for(ExpressionElement el : elements) {
    //         // TODO: omit expressions within brackets (only get top-level summands)
    //         if(el instanceof Operator) {
    //             Operator op = (Operator)el;
                
    //             // TODO: and if end != 0 because leading minus or plus elements are signs and not operators
    //             if(end != 0 && (op.equals(minus) || op.equals(plus))) {
    //                 List<ExpressionElement> subElements = elements.subList(start, end);
    //                 summands.add(new Expression(subElements));
    //                 start = op.equals(minus) ? end : end+1;
    //             }
    //         }
    //         end++;
    //     }
    //     if((elements.size() - start) > 0) summands.add(new Expression(elements.subList(start, elements.size())));

    //     return summands;
    // }

    // public boolean contains(ExpressionElement el) {
    //     return elements.contains(el);
    // }

    // public boolean containsParantheses() {
    //     return contains(Paranthesis.LEFT_PARANTHESIS) || contains(Paranthesis.RIGHT_PARANTHESIS);
    // }

}