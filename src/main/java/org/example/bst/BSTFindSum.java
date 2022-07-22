package org.example.bst;

import org.example.bst.util.BSTDeserialise;
import org.example.tree.util.TreeNode;

import java.util.Scanner;
import java.util.Stack;

public class BSTFindSum {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Integer[] arr = new Integer[n];
        for(int i = 0; i < n; i ++) {
            int x = sc.nextInt ();
            arr[i] = new Integer (x);
        }

        int sum = sc.nextInt ();
        TreeNode<Integer> bst = BSTDeserialise.deserializeFromArray(arr);

        System.out.println (isSumExists(bst, sum));
    }

    private static boolean isSumExists(TreeNode<Integer> bst, int sum) {
        Stack<TreeNode<Integer>> stack = new Stack<TreeNode<Integer>> ();

        TreeNode<Integer> cur = bst;
        // Go to the last node
        while(cur != null){
            stack.push(cur);
            cur = cur.getRight ();
        }
        return find(bst, stack, sum);
    }

    private static boolean find(TreeNode<Integer> bst, Stack<TreeNode<Integer>> stack, int sum) {
        if(bst == null || stack.isEmpty())
            return false;

        boolean a1 = find(bst.getLeft(), stack, sum);

        // Both at same point then just increment one of them;
        if(!stack.isEmpty() && bst == stack.peek())
            stack.pop();

        while(!stack.isEmpty() && bst.getVal() +  stack.peek().getVal() > sum){
            TreeNode<Integer> cur = stack.peek().getLeft();
            stack.pop();

            while(cur != null){
                stack.push(cur);
                cur = cur.getRight();
            }
        }

        if(!stack.isEmpty() && bst.getVal() + stack.peek().getVal() == sum)
            return true;
        boolean a2 = find(bst.getRight(), stack, sum);
        return false || a1 || a2;
    }
}
