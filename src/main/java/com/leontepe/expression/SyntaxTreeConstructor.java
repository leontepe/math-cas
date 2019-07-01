package com.leontepe.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.leontepe.function.Function;

public class SyntaxTreeConstructor {

    /**
     * Constructs a syntax tree from a postfix token list.
     */
    public static SyntaxTreeNode construct(List<ExpressionElement> postfix) {

        // Intialize node stack
        Stack<SyntaxTreeNode> nodeStack = new Stack<SyntaxTreeNode>();

        // Iterate through postfix token list
        for(ExpressionElement el : postfix) {

            if(el instanceof Number) {
                Number number = (Number)el;
                SyntaxTreeNode numberNode = new SyntaxTreeNode(number);
                nodeStack.push(numberNode);
            }
            else if(el instanceof Variable) {
                Variable variable = (Variable)el;
                SyntaxTreeNode variableNode = new SyntaxTreeNode(variable);
                nodeStack.push(variableNode);
            }
            else if(el instanceof Operator) {
                // Create operator node
                Operator op = (Operator)el;
                SyntaxTreeNode operatorNode = new SyntaxTreeNode(op);

                // Respectively pop 1 or 2 operands from stack and add them as children to the operator
                if(op.getArity() == 1) {
                    SyntaxTreeNode operandNode = nodeStack.pop();
                    operatorNode.addChild(operandNode);
                }
                else if(op.getArity() == 2) {
                    SyntaxTreeNode operandNode2 = nodeStack.pop();
                    SyntaxTreeNode operandNode1 = nodeStack.pop();
                    operatorNode.addChild(operandNode1);
                    operatorNode.addChild(operandNode2);
                }

                // Push operator + operands onto node stack
                nodeStack.push(operatorNode);
            }
            else if (el instanceof Function) {
                Function f = (Function) el;
                SyntaxTreeNode functionNode = new SyntaxTreeNode(f);
                int arity = f.getArity();
                for (int i = arity - 1; i >= 0; i++) {
                    functionNode.addChild(nodeStack.pop());
                }
                nodeStack.push(functionNode);            
            }
        }

        // Construction failed if more than one item is left in node stack
        if(nodeStack.size() > 1) {
            throw new RuntimeException("More than one item left in node stack");
        }

        return nodeStack.pop();
    }

    /**
     * Deconstructs a syntax tree to return the postfix token list that constructs
     * the tree. This is equivalent to a postorder traversal of the tree.
     */
    public static List<ExpressionElement> deconstruct(SyntaxTreeNode node) {

        // for syntax trees with multiary operators, trees need to be expanded to only
        // have binary operators instead

        List<ExpressionElement> postfix = new ArrayList<ExpressionElement>();
        for (SyntaxTreeNode child : node.getChildren()) {
            postfix.addAll(deconstruct(child));
        }
        postfix.add(node.getExpressionElement());
        return postfix;
    }

    public static SyntaxTreeNode expand(SyntaxTreeNode node) {
        int size = node.getChildren().size();
        if (size > 2) {
            node.addChild(node.getExpressionElement());
            for (int i = 1; i < size; i--) {
                node.getChildren().get(size - i + 1).addChild(node.remove(1));
            }
        }

        // Recursive call
        for (SyntaxTreeNode child : node.getChildren()) {
            expand(child);
        }

        return node;
    }
}