package org.example.hackerearth.julyeasy;

import java.util.*;
import java.io.*;
class TestClass {
    public static void main(String args[] ) throws Exception {
        Scanner sc = new Scanner(System.in);
       
            bst<Long> avl = new bst<>();

            long n = sc.nextInt();
            int k = sc.nextInt();
            System.out.println(n);
            while(n-- > 0) {
                long x = sc.nextInt();
                //System.out.print(x + " ");
                avl.insert(x);
                //System.out.println(avl.head.height);
            }

            while(k-- > 0) {
                avl.deleteMinimum();
                avl.deleteMaximum();
                long min = avl.getMin();
                long max = avl.getMax();

                avl.insert(2 * min);
                avl.insert(max % 2 == 0 ? (max / 2) : (max / 2 + 1));
            }
            System.out.println(avl.getSum());
    }
}

class Node<T extends Comparable> {
    int frequency = 0;

    public void increament(){
        frequency ++;
    }

    public void decreament(){
        frequency --;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    T val;
    int height;
    Node<T> left;
    Node<T> right;

    public Node(T val) {
        this.val = val;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public int compareTo(Node<T> node) {
        return this.val.compareTo(node.getVal());
    }

    public void copy(Node<T> node) {
        this.setVal(node.getVal());
        this.setFrequency(node.getFrequency());
    }

    public long getSum() {
        return (Long)val * (long)frequency;
    }
}

class bst<T extends Comparable>{
    Node<T> head;
    private T min;
    private T max;
    private int height(Node<T> node){
        if(node == null)
            return 0;
        return node.getHeight();
    }

    private int getBalance(Node<T> node){
        if(node == null)
            return 0;
        return height(node.getLeft()) - height(node.getRight());
    }

    public Node<T> insert(T val) {
        Node<T> node = new Node<>(val);
        head = insert(head, node);
        return head;
    }

    private Node<T> insert(Node<T> head, Node<T> node) {
        if(head == null){
            head = node;
            node.setHeight(1);
            node.setFrequency(1);
            return head;
        }

        if(head.compareTo(node) > 0)
            head.setLeft(insert(head.getLeft(), node));
        else if(head.compareTo(node) < 0)
            head.setRight(insert(head.getRight(), node));
        else {
            // Already Exists
            head.increament();
            return head;
        }

        head.setHeight(1 + Math.max(height(head.getLeft()), height(head.getRight())));
        return balance(head);
    }


    public Node<T> deleteMinimum(){head = deleteMinimum(head); return head;}
    public Node<T> deleteMaximum(){head = deleteMaximum(head); return head;}

    public long getSum() {
        return getInorderSum(head);
    }

    private Node<T> deleteMinimum(Node<T> node) {
        if(node.getLeft() == null){
            node.decreament();
            min = node.getVal();
            if(node.getFrequency() == 0) {
                return node.getRight();
            }
            return node;
        }

        node.setLeft(deleteMinimum(node.getLeft()));

        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
        return balance(node);
    }

    private Node<T> deleteMaximum(Node<T> node) {
        if(node.getRight() == null){
            node.decreament();
            max = node.getVal();
            if(node.getFrequency() == 0) {
                return node.getLeft();
            }
            return node;
        }

        node.setRight(deleteMaximum(node.getRight()));

        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
        return balance(node);
    }

    private long getInorderSum(Node<T> head) {
        if(head == null)
            return 0;
        return head.getSum() + getInorderSum(head.getLeft()) + getInorderSum(head.getRight());
    }

    private Node<T> balance(Node<T> node) {
        int balanceVal = getBalance(node);

        // left left
        if(balanceVal > 1 && getBalance(node.getLeft()) >= 0)
            return rightRotate(node);

        // left right
        if(balanceVal > 1 && getBalance(node.getLeft()) < 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        // left left
        if(balanceVal < -1 && getBalance(node.getRight()) <= 0)
            return leftRotate(node);

        // left right
        if(balanceVal < -1 && getBalance(node.getRight()) > 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    private Node<T> rightRotate(Node<T> node) {
        Node<T> t = node.getLeft();
        Node<T> r = t.getRight();

        t.setRight(node);
        node.setLeft(r);

        t.setHeight(1 + Math.max(height(t.getLeft()), height(t.getRight())));
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));

        return t;
    }

    private Node<T> leftRotate(Node<T> node) {
        Node<T> t = node.getRight();
        Node<T> l = t.getLeft();

        t.setLeft(node);
        node.setRight(l);

        t.setHeight(1 + Math.max(height(t.getLeft()), height(t.getRight())));
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));

        return t;
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
    }
}