
package com.leontepe.expression;

import java.util.*;

import com.leontepe.expression.Operator.Associativity;
import com.leontepe.expression.Operator.BinaryOperator;
import com.leontepe.expression.Operator.UnaryOperator;
import com.leontepe.function.Function;
import com.leontepe.MathContext;
import com.leontepe.exception.*;

public class Expression extends ExpressionElement {

    // private SyntaxTreeNode syntaxTreeRoot;

    // public Expression(String expressionString) {
    // List<ExpressionElement> infix =
    // ExpressionTokenizer.tokenize(expressionString);
    // List<ExpressionElement> postfix = NotationConverter.infixToPostfix(infix);
    // this.syntaxTreeRoot = SyntaxTreeConstructor.construct(postfix);
    // }

    // public SyntaxTreeNode getSyntaxTree() { return this.syntaxTreeRoot.clone(); }

    private List<ExpressionElement> expressionElements;

    public Expression(String expressionString) throws InvalidSyntaxException, AmbiguousSyntaxException, NotImplementedException {
        this(new MathContext(), expressionString);
    }

    public Expression(MathContext context, String expressionString) throws InvalidSyntaxException, AmbiguousSyntaxException, NotImplementedException {
        this.expressionElements = ExpressionTokenizer.tokenize(expressionString, context);
    }

    public Expression(List<ExpressionElement> expressionElements) {
        this.expressionElements = expressionElements;
    }

    public List<ExpressionElement> getExpressionElements() {
        return this.expressionElements;
    }

    @Override
    public String toString() {
        String expressionString = "";
        for (ExpressionElement el : expressionElements) {
            expressionString += el.toString();
        }
        return expressionString;
    }

    public boolean isEvaluatable() {
        for (Variable var : getVariables()) {
            if (!var.hasValue()) {
                return false;
            }
        }
        return true;
    }

    public Number evaluate() {

        if (!isEvaluatable()) {
            return null;
        }

        List<ExpressionElement> postfix = NotationConverter.infixToPostfix(expressionElements);
        Stack<Number> numberStack = new Stack<Number>();

        // possible element types: number, operator, function
        for (ExpressionElement el : postfix) {
            if (el instanceof Number) {
                Number num = (Number) el;
                numberStack.push(num);
            }
            else if (el instanceof Variable) {
                Variable var = (Variable) el;
                Number num = var.getValue();
                numberStack.push(num);
            }
            else if (el instanceof Operator) {
                Operator op = (Operator) el;

                if (op.getArity() == 2) {
                    BinaryOperator binOp = (BinaryOperator) op;
                    Number operand2 = numberStack.pop();
                    Number operand1 = numberStack.pop();
                    Number result = binOp.operate(operand1, operand2);
                    numberStack.push(result);

                }
                else if (op.getArity() == 1) {
                    UnaryOperator binOp = (UnaryOperator) op;
                    Number operand = numberStack.pop();
                    Number result = binOp.operate(operand);
                    numberStack.push(result);

                }
            }
            else if (el instanceof Function) {
                Function f = (Function) el;
                int arity = f.getArity();
                Number[] arguments = new Number[arity];
                for (int i = arity - 1; i >= 0; i--) {
                    arguments[i] = numberStack.pop();
                }
                Number result = f.apply(arguments);
                numberStack.push(result);
            }
        }

        return numberStack.pop();
    }

    public List<Variable> getVariables() {
        List<Variable> variables = new ArrayList<Variable>();
        for (ExpressionElement el : expressionElements) {
            if (el instanceof Variable) {
                Variable var = (Variable) el;
                variables.add(var);
            }
        }
        return variables;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Expression) {
            Expression ex = (Expression) obj;
            return expressionElements.equals(ex.getExpressionElements());
        }
        return false;
    }

    public SyntaxTreeNode constructSyntaxTree() {
        return SyntaxTreeConstructor.construct(NotationConverter.infixToPostfix(expressionElements));
    }

    public void printSyntaxTree() {
        constructSyntaxTree().print();
    }

    public boolean isFunctionLeftSide() {
        // f(x), g(x), f(t) = ...
        if (expressionElements.size() == 4) {
            if (expressionElements.get(0) instanceof Function
                    && expressionElements.get(1) == Parenthesis.LEFT_PARENTHESIS
                    && expressionElements.get(2) instanceof Variable
                    && !((Variable)expressionElements.get(2)).hasValue()
                    && expressionElements.get(3) == Parenthesis.RIGHT_PARENTHESIS) {
                return true;
            }
        }
        // y = ...
        // else if (expressionElements.size() == 1) {
        // if (expressionElements.get(0) instanceof Variable &&
        // !(expressionElements.get(0) instanceof Constant)) {
        // return true;
        // }
        // }

        return false;
    }

    // this should technically have a binary syntax tree as input, so assume it is
    // binary
    // private static void equalize(SyntaxTreeNode node) {

    // }

    // private static SyntaxTreeNode transformNegatives(SyntaxTreeNode node) {
    // if()
    // }

    // private static SyntaxTreeNode levelOperators() {

    // }

    // private static SyntaxTreeNode simplifyRationals() {

    // }

    // public Expression substitute(Variable variable, Number number);
    // public List<Expression> getSummands();
    // public boolean contains(ExpressionElement el);
    // public boolean containsParantheses();

}