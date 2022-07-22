package org.example.segmenttree.main;

import org.example.segmenttree.util.SegementTree;

import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RMQ {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Integer[] ar = new Integer[n];
        for(int i = 0; i < n; i  ++)
            ar[i] = new Integer(sc.nextInt());

        //Value tranformation for segement tree.
        Function<Integer, Integer> getSegmentTreeValue = (Integer i) -> {
            return i;
        };

        // segement Join function
        BiFunction<Integer, Integer, Integer> joinSegement = (Integer i1, Integer i2) -> {
            return Integer.max(i1, i2);
        };
    }
}
