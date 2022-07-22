package org.example.segmenttree.main;

import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LinkedSegementTree{
    static class LinkedSegementTreeNode {
        public LinkedSegementTreeNode left;
        public LinkedSegementTreeNode right;
        public int val;

        public LinkedSegementTreeNode(int val) {
            this.val = val;
        }
    }

    public static int addRangeValue(LinkedSegementTreeNode head, int val, int start, int end, int startIndex, int endIndex) {
        if(startIndex <= start && end <= endIndex) {
            head.val = head.val + val;
            return head.val;
        }

        int mid = (start + end) / 2;

        if(endIndex <= mid) {
            if(head.left == null)
                head.left = new LinkedSegementTreeNode(0);
            head.val = Integer.max(head.val, addRangeValue(head.left, val, start, mid, startIndex, endIndex));
            return head.val;
        }
        if(startIndex > mid) {
            if(head.right == null)
                head.right = new LinkedSegementTreeNode(0);
            head.val = Integer.max(head.val, addRangeValue(head.right, val, mid + 1, end, startIndex, endIndex));
            return head.val;
        }

        if(head.left == null)
            head.left = new LinkedSegementTreeNode(0);
        if(head.right == null)
            head.right = new LinkedSegementTreeNode(0);

        head.val = Integer.max(addRangeValue(head.left, val, start, mid, startIndex, mid), addRangeValue(head.right, val, mid + 1, end, mid + 1, endIndex));

        return head.val;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 100000000;
        int q = sc.nextInt();

        // Head of segement tree
        LinkedSegementTreeNode head = new LinkedSegementTreeNode(0);

        while(q -- > 0) {
            int x = sc.nextInt();
            if(x == 0)
                System.out.println(head.val);
            else {
                int l = sc.nextInt();
                int r = sc.nextInt();
                addRangeValue(head, r, 0, n, l, l + r);
            }
        }
    }
}