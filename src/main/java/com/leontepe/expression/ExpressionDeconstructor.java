package com.leontepe.expression;

import java.util.ArrayList;
import java.util.List;

import com.leontepe.expression.Operator.Arity;
import com.leontepe.expression.Operator.UnaryOperator;

public class ExpressionDeconstructor {

    public static List<ExpressionElement> deconstruct(SyntaxTreeNode node) {
        List<ExpressionElement> infix = new ArrayList<ExpressionElement>();
        if (node.isLeaf()) {
            infix.add(node.getExpressionElement());
        }
        else {
            Operator op = (Operator)node.getExpressionElement();
            boolean parenthesize = !node.isRoot() && (node.getParent().getExpressionElement() instanceof Operator)
                    && ((Operator) node.getParent().getExpressionElement()).getPrecedence() > ((Operator)node.getExpressionElement()).getPrecedence();
            
            if (parenthesize) infix.add(Parenthesis.LEFT_PARENTHESIS);
            if(op.getArity() == Arity.UNARY) {
                // since only unary prefix operators exist right now
                infix.add(node.getExpressionElement());
                infix.addAll(deconstruct(node.getChildren().get(0)));
            }
            else {
                infix.addAll(deconstruct(node.getChildren().get(0)));
                infix.add(node.getExpressionElement());
                infix.addAll(deconstruct(node.getChildren().get(1)));
            }
            if(parenthesize) infix.add(Parenthesis.RIGHT_PARENTHESIS);

            

            
        }
        return infix;
    }
}