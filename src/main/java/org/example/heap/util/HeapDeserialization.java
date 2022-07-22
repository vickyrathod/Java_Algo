package org.example.heap.util;

import org.example.tree.util.TreeNode;

import java.util.List;

public class HeapDeserialization {


    public <T extends Comparable> TreeNode<T> buildHeapFromInorder(List<T> inorder, int start, int end) {
        if(start > end)
            return null;
        int maxIndex = 0;
        for(int i = start; i <= end; i ++) {
            if(inorder.get(i).compareTo(inorder.get(maxIndex)) > 0)
                maxIndex = i;
        }
        return new TreeNode<>(buildHeapFromInorder(inorder, start, maxIndex - 1), buildHeapFromInorder(inorder, maxIndex + 1, end), inorder.get(maxIndex));
    }
}
