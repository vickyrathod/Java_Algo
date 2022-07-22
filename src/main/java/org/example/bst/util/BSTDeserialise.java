package org.example.bst.util;

import org.example.tree.util.TreeNode;

public class BSTDeserialise {

    public static <T extends Comparable> TreeNode<T> deserializeFromArray(T[] arr){
        TreeNode<T> bst = null;
        for(T t : arr)
            bst = BSTOperation.insert(bst, new TreeNode<T>(null, null, t));

        return bst;
    }
}
