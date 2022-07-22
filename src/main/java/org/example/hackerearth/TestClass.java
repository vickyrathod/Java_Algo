package org.example.hackerearth;

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
class TestClass {
    public static void main(String args[] ) throws Exception {
        AVL<Integer> avl = new AVL<>();
        Random r = new Random();
        int n = 5;
        int k = 6;
        long sum = 0;
        int[] arr = {10 ,7 ,4 ,2 ,1};
        int i = 0;
        while(n -- > 0) {

            sum += arr[i];
            avl.insert(arr[i]);
            i++;
        }
        Arrays.sort(arr);
        while(k -- > 0) {

            sum -= arr[arr.length - 1]/2;
            sum += arr[0];
            arr[0] = 2 * arr[0];
            arr[arr.length - 1] = arr[arr.length - 1] - arr[arr.length - 1]/2;

            Arrays.sort(arr);
            avl.deleteMaximum();
            avl.deleteMinimum();
            avl.insert(avl.getMin() * 2);
            avl.insert(avl.getMax() - avl.getMax()/2);
        }
        System.out.println(avl.getSum() + " " + sum);
    }
}

class Countable {
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
}
class Node<T extends Comparable> extends Countable {
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
        return (Integer) val * (long)frequency;
    }
}

class AVL<T extends Comparable>{
    private Node<T> head;
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

    private Node<T>  getMinimum(Node<T> node){
        while(node.getLeft() != null)
            node = node.getLeft();
        return node;
    }

    public Node<T> delete(Node<T> head, Node<T> node){

        if(head == null)
            return null;
        if(head.compareTo(node) > 0)
            head.setLeft(delete(head.getLeft(), node));
        else if(head.compareTo(node) < 0)
            head.setRight(delete(head.getRight(), node));
        else {
            if(head.getLeft() == null || head.getRight() == null) {
                if(head.getLeft() != null)
                    return head.getLeft();
                if(head.getRight() != null)
                    return head.getRight();
                return null;
            }

            // find inorder succesor
            Node<T> minimum = getMinimum(head.getRight());
            head.copy(minimum);
            head.setRight(delete(head.getRight(), minimum));
        }

        head.setHeight(1 + Math.max(height(head.getLeft()), height(head.getRight())));
        return balance(head);
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
