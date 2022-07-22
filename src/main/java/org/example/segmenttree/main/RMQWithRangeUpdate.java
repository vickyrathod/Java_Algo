package org.example.segmenttree.main;

import org.example.segmenttree.util.SegementTree;
import org.example.segmenttree.util.SegmentTreeLazzyPropagation;

import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RMQWithRangeUpdate {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Integer[] ar = new Integer[n];
        for(int i = 0; i < n; i  ++)
            ar[i] = new Integer(sc.nextInt());

        //Value tranformation for segement tree.
        Function<Integer, Integer> getSegmentTreeValue = (Integer i) -> i * i;

        // Update range value Increament function
        BiFunction<Integer, Integer, Integer> updateValueOfSegment = (Integer t1, Integer t2) -> {
            if(t1 == null)
                return t2;
            if(t2 == null)
                return t1;

            return t1 + t2;
        };
        BiFunction<Integer, Integer, Integer> returnUpdatedValueOfSegment = (Integer t1, Integer t2) -> {
            if(t1 == null)
                return t2;
            if(t2 == null)
                return t1;

            return Integer.max(t1 , t2);
        };
        // segement Join function
        BiFunction<Integer, Integer, Integer> joinSegement = (Integer i1, Integer i2) -> {
            return Integer.max(i1, i2);
        };

        SegementTree<Integer, Integer> segementTree = new SegementTree<>(n, getSegmentTreeValue, joinSegement, updateValueOfSegment,returnUpdatedValueOfSegment);
        segementTree.bulidSegementTree(0, 0, n - 1, ar);

        int q = sc.nextInt();

        while(q -- > 0) {
            int x = sc.nextInt();
            int l = sc.nextInt();
            int r = sc.nextInt();
            if(x == 0)
                System.out.println(segementTree.executeQuery(0, 0, n - 1, l, r));
            else {
                int val = sc.nextInt();
                segementTree.updateSegement(0, 0, n - 1, l, r, new Integer(val));
            }
        }
    }
}
