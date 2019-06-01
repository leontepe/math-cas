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
     * Adds a child to this node's list of children.
     * @param child The data of the child to be added.
     */
    public void addChild(T child) {
        Node<T> childNode = new Node<T>(child);
        childNode.parent = this;
        this.children.add(new Node<T>(child));
    }
}