package org.example.tree.util;

public class CompleteTreeDeserialize {

    private static <T extends Comparable> TreeNode<T> buildTreeFromArray(int i, T[] arr) {
        if(i >= arr.length)
            return null;
        if(arr[i] instanceof Integer)
            if((Integer) arr[i] == -1)
                return null;
        return new TreeNode<T>(buildTreeFromArray(2*i + 1, arr), buildTreeFromArray(2*i + 2, arr), arr[i]);
    }
    public static <T extends Comparable> TreeNode<T> getTreeFromArray(T[] arr){
       return buildTreeFromArray(0, arr);
    }
}
