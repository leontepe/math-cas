
package com.leontepe.expression;

import java.util.*;

import com.leontepe.expression.Operator.Arity;
import com.leontepe.expression.Operator.Associativity;
import com.leontepe.expression.Operator.UnaryOperator;
import com.leontepe.exception.EvaluationException;
import com.leontepe.exception.ExpressionParsingException;

public class Expression extends ExpressionElement {
    
    private SyntaxTreeNode syntaxTreeRoot;

    public Expression(String expressionString) {
        List<ExpressionElement> infix = ExpressionTokenizer.tokenize(expressionString);
        List<ExpressionElement> postfix = NotationConverter.infixToPostfix(infix);
        this.syntaxTreeRoot = SyntaxTreeConstructor.construct(postfix);
    }
    
    public SyntaxTreeNode getSyntaxTree() { return this.syntaxTreeRoot.clone(); }
    
    @Override
    public String getStringValue() {
        String expressionString = "";
        List<ExpressionElement> els = ExpressionDeconstructor.deconstruct(syntaxTreeRoot);
        for(ExpressionElement el : els) {
            expressionString += el.getStringValue();
        }
        return expressionString;
    }

    // private void checkExpression();

    // private void checkParantheses();

    public Number evaluate() {

        // cannot evaluate expressions with variables
        // if(getVariables().size() > 0) {
        //     throw new EvaluationException();
        // }

        return syntaxTreeRoot.evaluate();
    }

    // public List<Variable> getVariables();

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Expression) {
            Expression ex = (Expression)obj;
            return ex.getSyntaxTree().equals(this.syntaxTreeRoot);
        }
        return false;
    }


    // this should technically have a binary syntax tree as input, so assume it is binary
    private static void equalize(SyntaxTreeNode node) {

    }

    private static SyntaxTreeNode transformNegatives(SyntaxTreeNode node) {
        if()
    }
    
    private static SyntaxTreeNode levelOperators() {
        
    }

    private static SyntaxTreeNode simplifyRationals() {
        
    }

    // public Expression substitute(Variable variable, Number number);
    // public List<Expression> getSummands();
    // public boolean contains(ExpressionElement el);
    // public boolean containsParantheses();
    

}