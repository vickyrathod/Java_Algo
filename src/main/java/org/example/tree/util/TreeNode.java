package org.example.tree.util;

public class TreeNode<T extends Comparable> implements Comparable{
    private TreeNode right;
    private TreeNode left;
    private T val;

    public TreeNode(){

    }

    public TreeNode(TreeNode left, TreeNode right,  T val) {
        this.right = right;
        this.left = left;
        this.val = val;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
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
        TreeNode<T> t = (TreeNode<T>) o;
        return getVal().compareTo(t.getVal());
    }
}
