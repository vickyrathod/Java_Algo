package org.example.bst.util;

import org.example.tree.util.TreeNode;

import java.util.Optional;

public class BSTOperation {

    public static <T extends Comparable> TreeNode<T> insert(TreeNode<T> bst, TreeNode<T> t){
        if(bst == null)
            return t;
        if(bst.compareTo(t) > 0)
            bst.setLeft(insert(bst.getLeft(),  t));
        else
            bst.setRight(insert(bst.getRight(),  t));
        return bst;
    }

    public static <T extends Comparable> Optional<TreeNode<T>> search(TreeNode<T> bst, T t) {
        if(bst == null)
            return Optional.empty();
        if(bst.getVal().compareTo(t) > 0)
            return search(bst.getLeft(), t);
        if(bst.getVal().compareTo(t) < 0)
            return search(bst.getRight(), t);
        return Optional.of(bst);
    }

    public static <T extends Comparable> TreeNode<T> delete(TreeNode<T> bst,  TreeNode<T> t){
        if(bst == null)
            return null;
        if(bst.compareTo(t) > 0)
            bst.setLeft(delete(bst.getLeft(), t));
        else if(bst.compareTo(t) < 0)
            bst.setRight(delete(bst.getRight(), t));
        //Delete node here and create tree
        else {
            //Find minimum from right tree
            TreeNode<T> min = findMin(bst.getRight());
            // If right tree is empty then first node of left is new root
            if(min == null)
                min = bst.getLeft();
            // Delete the new min node from its location
            else {
                delete(bst.getRight(), min);
            }
            //Delete node
            bst.setLeft(null);
            bst.setRight(null);

            return min;
        }
        return bst;
    }

    public static <T extends Comparable> TreeNode<T> findMin(TreeNode<T> bst) {
        if(bst.getLeft() != null)
            return findMin(bst.getLeft());
        return bst;
    }

    public static <T extends Comparable> TreeNode<T> findMax(TreeNode<T> bst) {
        if(bst.getRight() != null)
            return findMax(bst.getRight());
        return bst;
    }
}
