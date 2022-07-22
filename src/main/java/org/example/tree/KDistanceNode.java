package org.example.tree;

import org.example.bst.util.BSTDeserialise;
import org.example.tree.util.CompleteTreeDeserialize;
import org.example.tree.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class KDistanceNode {

    private static void getNodeAtDistanceFromGivenNode(TreeNode<Integer> cur, int d, List<Integer> ans){
        if(cur == null || d < 0)
            return;
        if(d == 0){
            ans.add(cur.getVal());
            return;
        }

        getNodeAtDistanceFromGivenNode(cur.getLeft(), d - 1, ans);
        getNodeAtDistanceFromGivenNode(cur.getRight(), d - 1, ans);
    }

    private static int getKDistanceNode(TreeNode<Integer> A, TreeNode<Integer> B, int C, List<Integer> ans){
        //If node not found in tree
        if(A == null)
            return Integer.MIN_VALUE;
        if(A.compareTo(B) == 0) {
            // Check for current node distance
            if(C == 0)
                ans.add(A.getVal());
            else {
                getNodeAtDistanceFromGivenNode(A.getLeft(), C - 1, ans);
                getNodeAtDistanceFromGivenNode(A.getRight(), C - 1, ans);
            }
            return 1;
        }
        // d is distance from the found node.
        int d2 = getKDistanceNode(A.getRight(), B, C, ans);
        int d1 = getKDistanceNode(A.getLeft(), B, C, ans);
        int d = d1;
        if(d1 > 0) {
            // Check for current node distance
            if(C- d1 == 0)
                ans.add(A.getVal());
            else
                getNodeAtDistanceFromGivenNode(A.getRight(), C - d1 - 1, ans);
            return d1 + 1;
        } else if(d2 > 0) {
            // Check for current node distance
            if(C- d1 == 0)
                ans.add(A.getVal());
            else
                getNodeAtDistanceFromGivenNode(A.getLeft(), C - d2 - 1, ans);
            return d2 + 1;
        }

        return Integer.MIN_VALUE;
    }

    public static List<Integer> solve(TreeNode<Integer> A, int B, int C) {
        List<Integer> ans = new ArrayList<>();
        getKDistanceNode(A, new TreeNode<Integer>(null, null, B), C, ans);

        return ans;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> as= new ArrayList<>();
        as.stream().map(x -> x.toString()).forEach(System.out::println);
        Random ran = new Random();
        //ran.
        //Integer[] arr = new Integer[n];
        for(int i = 0; i < n; i ++) {
            int x = sc.nextInt();
            //arr[i] = new Integer(x);
        }
        int b = sc.nextInt();
        int k = sc.nextInt();
        //TreeNode<Integer> tree = CompleteTreeDeserialize.getTreeFromArray(arr);

        //solve(tree, b , k);
    }
}
