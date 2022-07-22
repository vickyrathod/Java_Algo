package org.example.bst;

public class SelfBalancedBst {
    public static void main(String args[]) {
        int n = 10000000;
        AVL<Integer> bst = new AVL<>();

        for(int i = 0; i < n; i ++) {
            bst.insert(new Node<>(i, i));
        }

        for(int i = 0; i < 10000; i ++) {
            bst.delete(new Node<>(i, i));
        }
        // height should always less than or equal to log(n) + 1
        System.out.println(bst.getHeight());
        System.out.println(bst.contains(new Node<>(200000, 0)));
    }
}

interface Copiable<T> {
    public void copy(T t);
}

class Node<T extends Comparable> implements Comparable<Node>, Copiable<Node> {
    private T val;
    private int height = 0;
    private Node<T> left;
    private Node<T> right;

    public Node(T val, int count){
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

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int compareTo(Node o) {
       return this.val.compareTo(o.getVal());
    }

    @Override
    public void copy(Node node) {
        this.val = (T)node.getVal();
    }
}

class AVL<T extends Comparable> {

    // Root Node
    Node<T> head;

    // AVL Right Rotation
    private Node<T> rightRotate(Node<T> node) {
        Node<T> t = node.getLeft();
        Node<T> r = t.getRight();
        t.setRight(node);
        node.setLeft(r);
        // set Heights
        t.setHeight(1 + Math.max(getHeight(t.getLeft()), getHeight(t.getRight())));
        node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));
        return t;
    }

    // AVL Left Rotation
    private Node<T> leftRotate(Node<T> node) {
        Node<T> t = node.getRight();
        Node<T> l = t.getLeft();
        t.setLeft(node);
        node.setRight(l);
        // set Heights
        t.setHeight(1 + Math.max(getHeight(t.getLeft()), getHeight(t.getRight())));
        node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));
        return t;
    }

    public Node<T> findMinimum(Node<T> head) {
        if(head == null)
            return null;

        while(head.getLeft() != null)
            head = head.getLeft();

        return head;
    }

    private int getBalanced(Node<T> node) {
        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    private int getHeight(Node<T> node) {
        if(node == null)
            return 0;
        return node.getHeight();
    }

    public int getHeight(){
        return getHeight(head);
    }

    public boolean contains(Node<T> head, Node<T> targetNode) {
        if(head == null)
            return false;

        if(head.compareTo(targetNode) > 0)
            return contains(head.getLeft(), targetNode);
        else if(head.compareTo(targetNode) < 0)
            return contains(head.getRight(), targetNode);

        return true;
    }

    // Overloaded contains
    public boolean contains(Node<T> t) {
        return contains(head, t);
    }

    private Node<T> insert(Node<T> head, Node<T> node) {
        // End condition
        if(head == null) {
            head = node;
            head.setHeight(1);
            return head;
        }

        if(head.compareTo(node) > 0)
            head.setLeft(insert(head.getLeft(), node));
        else if(head.compareTo(node) < 0)
            head.setRight(insert(head.getRight(), node));
        else
            // No duplicate allowed
            return head;

        head.setHeight(1 + Math.max(getHeight(head.getLeft()), getHeight(head.getRight())));
        return balance(head);
    }

    public Node<T> insert(Node<T> node) {
        this.head = insert(head, node);
        return head;
    }

    private Node<T> delete(Node<T> head, Node<T> node) {

        if(head == null)
            return null;
        if(head.compareTo(node) > 0)
            head.setLeft(delete(head.getLeft(), node));
        else if(head.compareTo(node) < 0)
            head.setRight(delete(head.getRight(), node));
        else {
            if(head.getLeft() == null || head.getRight() == null) {
                if(head.getLeft() != null) {
                    head.copy(head.getLeft());
                    head.setLeft(delete(head.getLeft(), head.getLeft()));
                } else if(head.getRight() != null) {
                    head.copy(head.getRight());
                    head.setRight(delete(head.getRight(), head.getRight()));
                } else {
                    // If head is deleted
                    return null;
                }
            } else {
                // Inorder succesor of node
                Node<T> succesor = findMinimum(head.getRight());
                head.copy(succesor);
                head.setRight(delete(head.getRight(), succesor));
            }
        }

        head.setHeight(1 + Math.max(getHeight(head.getLeft()), getHeight(head.getRight())));

        return balance(head);
    }

    public Node<T> delete(Node<T> node) {
        head = delete(head, node);
        return head;
    }

    private Node<T> balance(Node<T> head) {
        int balanceVal = getBalanced(head);
        // Left left case
        if(balanceVal > 1 && getBalanced(head.getLeft()) >= 0)
            return rightRotate(head);
        // Left Right case
        if(balanceVal > 1 && getBalanced(head.getLeft()) < 0) {
            head.setLeft(leftRotate(head.getLeft()));
            return rightRotate(head);
        }

        // Right Right case
        if(balanceVal < -1 && getBalanced(head.getRight()) <= 0)
            return leftRotate(head);

        // right Left rotation
        if(balanceVal < -1 && getBalanced(head.getRight()) > 0) {
            head.setRight(rightRotate(head.getRight()));
            return leftRotate(head);
        }

        return head;
    }
}