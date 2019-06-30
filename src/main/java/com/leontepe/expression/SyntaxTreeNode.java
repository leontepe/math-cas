package com.leontepe.expression;

import java.util.List;

import com.leontepe.exception.EvaluationException;
import com.leontepe.expression.*;
import com.leontepe.expression.Number;
import com.leontepe.expression.Operator.Arity;
import com.leontepe.expression.Operator.BinaryOperator;
import com.leontepe.expression.Operator.MultiaryOperator;
import com.leontepe.expression.Operator.UnaryOperator;

import java.util.LinkedList;

/**
 * 
 * A simple tree node data structure. Used to construct a syntax tree.
 * Source: https://github.com/gt4dev/yet-another-tree-structure.
 * 
 * @param <T> The type of node data to be stored
 */

public class SyntaxTreeNode implements Cloneable {
    private ExpressionElement expressionElement;
    private SyntaxTreeNode parent;
    private List<SyntaxTreeNode> children;
    
    public SyntaxTreeNode(ExpressionElement expressionElement) {
        this.expressionElement = expressionElement;
        this.children = new LinkedList<SyntaxTreeNode>();
    }

    private SyntaxTreeNode(ExpressionElement expressionElement, List<SyntaxTreeNode> children) {
        this.expressionElement = expressionElement;
        this.children = children;
    }

    /** @return The data that this node stores. */
    public ExpressionElement getExpressionElement() {
        return this.expressionElement;
    }

    public void setExpressionElement(ExpressionElement el) {
        this.expressionElement = el;
    }

    /** @return The parent of this node. If the node is a root node, returns {@code null}. */
    public SyntaxTreeNode getParent() {
        return this.parent;
    }

    /** @return The list of nodes that are a child to this node. */
    public List<SyntaxTreeNode> getChildren() {
        return this.children;
    }

    /** Returns true if this node has no parent, meaning it is the the root node. Returns false otherwise. */
    public boolean isRoot() {
        return parent == null;
    }

    /** Returns true if this node has no children, meaning it is a leaf (or terminal) node. Returns false otherwise. */
    public boolean isLeaf() {
        return children.isEmpty();
    }

    /**
     * Adds data as a child to this node.
     * @param child The data of the child to be added.
     */
    public SyntaxTreeNode addChild(ExpressionElement child) {
        SyntaxTreeNode childNode = new SyntaxTreeNode(child);
        return addChild(childNode);
    }

    /**
     * Adds a child node to this node.
     * @param child The node to be added as a child
     */
    public SyntaxTreeNode addChild(SyntaxTreeNode child) {
        child.parent = this;
        this.children.add(child);
        return child;
    }

    public boolean removeChild(SyntaxTreeNode child) {
        if(children.remove(child)) {
            child.parent = null;
            return true;
        }
        return false;
    }

    public SyntaxTreeNode remove(int index) {
        SyntaxTreeNode child = getChildren().get(index);
        removeChild(child);
        return child;
    }

    public void setParent(SyntaxTreeNode parent) {
        this.parent = parent;
    }

    @Override
    /**
     * Syntax trees only equal each other, if they are exactly identical. If the order of the children is somehow different, the trees are not equal anymore. If you are looking for mathematical equality, use the equals-method in Expression.
     */
    public boolean equals(Object obj) {
        if(obj instanceof SyntaxTreeNode) {
            SyntaxTreeNode other = (SyntaxTreeNode)obj;
            if(isLeaf()) return expressionElement.equals(other.expressionElement);
            else return expressionElement.equals(other.expressionElement) && children.equals(other.getChildren());
        }
        return false;
    }

    @Override
    public SyntaxTreeNode clone() {
        return new SyntaxTreeNode(expressionElement, new LinkedList<SyntaxTreeNode>(children));
    }

    /**
     * Prints the node tree.
     */
    public void print() {
        System.out.print(toString());
    }

    /**
     * Source: https://stackoverflow.com/a/8948691
     */
    private String toStringRecursive(String prefix, boolean isTail) {
        String s = prefix + (isTail ? "└── " : "├── ") + expressionElement.toString() + System.lineSeparator();
        for (int i = 0; i < children.size() - 1; i++) {
            s += children.get(i).toStringRecursive(prefix + (isTail ? "    " : "│   "), false);
        }
        if (children.size() > 0) {
            s += children.get(children.size() - 1).toStringRecursive(prefix + (isTail ? "    " : "│   "), true);
        }
        return s;
    }

    @Override
    public String toString() {
        return System.lineSeparator() + toStringRecursive("", true);
    }

    public Number evaluate() {
        if(isLeaf()) {
            if(expressionElement instanceof Number) {
                return (Number)expressionElement;
            }
            else throw new EvaluationException("Leaf node is not a number");
        }
        else {
            if(expressionElement instanceof Operator) {
                Operator op = (Operator)expressionElement;
                if(op.getArity() == Arity.UNARY) {
                    if(children.size() == 1) {
                        UnaryOperator unaryOp = (UnaryOperator)op;
                        return unaryOp.operate(children.get(0).evaluate());
                    } 
                    else throw new EvaluationException("Unary operator has more than one child");
                }
                else {
                    if(children.size() == 2) {
                        BinaryOperator binaryOp = (BinaryOperator)op;
                        return binaryOp.operate(children.get(0).evaluate(), children.get(1).evaluate());
                    }
                    else if(children.size() > 2) {
                        MultiaryOperator multiaryOp = (MultiaryOperator)op;
                        Number[] ops = new Number[children.size()-2];
                        for(int i = 2; i < children.size(); i++) {
                            ops[i] = children.get(i).evaluate();
                        }
                        return multiaryOp.operate(children.get(0).evaluate(), children.get(1).evaluate(), ops);
                    }
                    else throw new EvaluationException("Binary operator only has one child");
                }
            }
            else throw new EvaluationException("Non-leaf node is not an operator");
        }
    }
}