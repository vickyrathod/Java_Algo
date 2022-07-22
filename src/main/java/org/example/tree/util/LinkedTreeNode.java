package org.example.tree.util;

import java.util.LinkedList;
import java.util.Queue;

public class LinkedTreeNode<T extends Comparable> implements Comparable{
    LinkedTreeNode<T> next;
    private LinkedTreeNode<T> right;
    private LinkedTreeNode<T> left;
    private T val;

    public LinkedTreeNode(T val) {
        this.val = val;
    }

    public LinkedTreeNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedTreeNode<T> next) {
        this.next = next;
    }

    public LinkedTreeNode<T> getRight() {
        return right;
    }

    public void setRight(LinkedTreeNode<T> right) {
        this.right = right;
    }

    public LinkedTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(LinkedTreeNode<T> left) {
        this.left = left;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    @Override
    public int compareTo(Object o) {
        LinkedTreeNode<T> t = (LinkedTreeNode<T>) o;
        return getVal().compareTo(t.getVal());
    }
}
