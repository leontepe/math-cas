package com.leontepe.syntaxtree;

import java.util.List;
import java.util.LinkedList;

/**
 * 
 * A simple tree node data structure. Used to construct a syntax tree.
 * Source: https://github.com/gt4dev/yet-another-tree-structure.
 * 
 * @param <T> The type of node data to be stored
 */

public class Node<T> {
    private T data;
    private Node<T> parent;
    private List<Node<T>> children;
    
    public Node(T data) {
        this.data = data;
        this.children = new LinkedList<Node<T>>();
    }

    /** @return The data that this node stores. */
    public T getData() {
        return this.data;
    }

    /** @return The parent of this node. If the node is a root node, returns {@code null}. */
    public Node<T> getParent() {
        return this.parent;
    }

    /** @return The list of nodes that are a child to this node. */
    public List<Node<T>> getChildren() {
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
    public Node<T> addChild(T child) {
        Node<T> childNode = new Node<T>(child);
        return addChild(childNode);
    }

    /**
     * Adds a child node to this node.
     * @param child The node to be added as a child
     */
    public Node<T> addChild(Node<T> child) {
        child.parent = this;
        this.children.add(child);
        return child;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node == false) return false;
        Node<T> other = (Node<T>)obj;
        if(isLeaf()) return data.equals(other.data);
        else return data.equals(other.data) && children.equals(other.getChildren());
    }

    /**
     * Prints the node tree.
     */
    public void print() {
        printRecursive("", true);
    }

    /**
     * Source: https://stackoverflow.com/a/8948691
     */
    private void printRecursive(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + data.toString());
        for (int i = 0; i < children.size() - 1; i++) {
            children.get(i).printRecursive(prefix + (isTail ? "    " : "│   "), false);
        }
        if (children.size() > 0) {
            children.get(children.size() - 1)
                    .printRecursive(prefix + (isTail ?"    " : "│   "), true);
        }
    }
}