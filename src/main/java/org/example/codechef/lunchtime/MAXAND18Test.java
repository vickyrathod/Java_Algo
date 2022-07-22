package org.example.codechef.lunchtime;

import org.springframework.util.Assert;

import java.util.*;
import java.io.*;

public class MAXAND18Test {
    public static void main(String args[]) throws IOException {
        int t = 0;
        Random ran = new Random();
        boolean f = true;
        while(f) {

            int n = ran.nextInt(100000);

            int[] arr = new int[n];
            for(int i = 0; i < n; i ++)
                arr[i] = ran.nextInt(100000);

            int k = ran.nextInt(3);
            int ans = MAXAND18.getAns(k, arr);

            if(k == 0) {
                f = ans == 0;
                if(!f)
                    System.out.println(0 + " , " + ans + " => " + (ans == 0));
            } else if(k == 1) {
                long max = Long.MIN_VALUE;
                int newAns = 0;
                for(int i = 0; i < 32; i ++) {
                    int r = 1 << i;

                    long temp = getSum(arr, r);

                    if(max < temp){
                        newAns = r;
                        max = temp;
                    }
                }
                f = ans == newAns;
                if(!f)
                    System.out.println(newAns + " , " + ans + " => " + (ans == newAns));
            } else if(k == 2) {
                long max = Long.MIN_VALUE;
                int newAns = 0;
                for(int i = 0; i < 32; i ++) {
                    for(int j = 0; j < i; j ++) {
                        int r = 1 << i;
                        r = r | 1 << j;

                        long temp = getSum(arr, r);

                        if(max < temp) {
                            newAns = r;
                            max = temp;
                        }
                    }
                }
                f = ans == newAns;
                if(!f)
                    System.out.println(newAns + " , " + ans + " => " + (ans == newAns));
            }
        }
    }

    private static long getSum(int[] arr, int k) {
        long sum = 0;
        for(int x : arr)
            sum += (x & k);

        return sum;
    }
}