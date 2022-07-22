package org.example.stack;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MaxArrayTest {

    public static void testLeftMax(int[] a){
        int[] ans = MaxArray.getLeftMaxArray(a);
        Arrays.stream(a).mapToObj(x -> x + " ").forEach(System.out::print);
        System.out.println();
        Arrays.stream(ans).mapToObj(x -> (x == -1 ? -1 : a[x]) + " ").forEach(System.out::print);
    }

    public static void testRightMax(int[] a){
        Arrays.stream(a).mapToObj(x -> x + " ").forEach(System.out::print);

        int[] ans = MaxArray.getRightMaxArray(a);
        System.out.println();
        Arrays.stream(ans).mapToObj(x -> (x == -1 ? -1 : a[x]) + " ").forEach(System.out::print);
    }

    public static void main(String args[]){
        Random rand = new Random();
        int[] a = new int[10];

        for(int i = 0; i < 10; i++)
            a[i] = rand.nextInt(100);
        testLeftMax(a);
        System.out.println();
        testRightMax(a);
    }
}
