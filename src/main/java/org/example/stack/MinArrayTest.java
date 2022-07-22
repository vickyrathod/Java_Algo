package org.example.stack;

import java.util.Arrays;
import java.util.Random;

public class MinArrayTest {

    public static void testLeftMin(int[] a){
        int[] ans = MinArray.getLeftMinArray(a);
        Arrays.stream(a).mapToObj(x -> x + " ").forEach(System.out::print);
        System.out.println();
        Arrays.stream(ans).mapToObj(x -> (x == -1 ? -1 : a[x]) + " ").forEach(System.out::print);
    }

    public static void testRightMin(int[] a){
        Arrays.stream(a).mapToObj(x -> x + " ").forEach(System.out::print);

        int[] ans = MinArray.getRightMinArray(a);
        System.out.println();
        Arrays.stream(ans).mapToObj(x -> (x == -1 ? -1 : a[x]) + " ").forEach(System.out::print);
    }

    public static void main(String args[]){
        Random rand = new Random();
        int[] a = new int[10];

        for(int i = 0; i < 10; i++)
            a[i] = rand.nextInt(100);
        testLeftMin(a);
        System.out.println();
        testRightMin(a);
    }
}
