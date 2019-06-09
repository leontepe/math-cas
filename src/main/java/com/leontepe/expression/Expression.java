
package com.leontepe.expression;

import java.util.*;

import com.leontepe.expression.Operator.Arity;
import com.leontepe.expression.Operator.Associativity;
import com.leontepe.syntaxtree.Node;
import com.leontepe.NotationConverter;
import com.leontepe.exception.EvaluationException;
import com.leontepe.exception.ExpressionParsingException;

public class Expression extends ExpressionElement {
    
    private Node<ExpressionElement> syntaxTree;

    public Expression(String expressionString) {
        List<ExpressionElement> infix = tokenize(expressionString);
        List<ExpressionElement> postfix = NotationConverter.infixToPostfix(infix);
        this.syntaxTree = constructSyntaxTree(postfix);
    }

    public Expression(List<ExpressionElement> infix) {
        List<ExpressionElement> postfix = NotationConverter.infixToPostfix(infix);
        this.syntaxTree = constructSyntaxTree(postfix);
    }
    
    public Node<ExpressionElement> getSyntaxTree() { return this.syntaxTree; }
    
    @Override
    public String getStringValue() {
        // TODO: Implement; try to fully and exactly reconstruct initial expression string from syntax tree.
        return "";
    }

    /**
     * Constructs a syntax tree from a postfix token list.
     */
    private static Node<ExpressionElement> constructSyntaxTree(List<ExpressionElement> postfix) {

        // Intialize node stack
        Stack<Node<ExpressionElement>> nodeStack = new Stack<Node<ExpressionElement>>();

        // Iterate through postfix token list
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
                // Create operator node
                Operator op = (Operator)el;
                Node<ExpressionElement> operatorNode = new Node<ExpressionElement>(op);

                // Respectively pop 1 or 2 operands from stack and add them as children to the operator
                switch(op.getArity()) {
                    case UNARY:
                        Node<ExpressionElement> operandNode = nodeStack.pop();
                        operatorNode.addChild(operandNode);
                    case BINARY:
                        Node<ExpressionElement> operandNode2 = nodeStack.pop();
                        Node<ExpressionElement> operandNode1 = nodeStack.pop();
                        operatorNode.addChild(operandNode1);
                        operatorNode.addChild(operandNode2);
                }

                // Push operator + operands onto node stack
                nodeStack.push(operatorNode);
            }
            else throw new RuntimeException("Unknown token type");
        }

        // Construction failed if more than one item is left in node stack
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