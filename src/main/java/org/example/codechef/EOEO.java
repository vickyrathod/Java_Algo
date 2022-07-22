package org.example.codechef;

import java.util.Scanner;

public class EOEO {

    private static int level(long x){
        int l = 0;
        while(x % 2 == 0)
            x = x / 2;

        return l;
    }

    static long power(int x, int y) {
        int res = 1;     // Initialize result

        while (y > 0)
        {
            // If y is odd, multiply x with result
            if (y % 2 != 0)
                res = res*x;

            // y must be even now
            y = y>>1; // y = y/2
            x = x*x;  // Change x to x^2
        }
        return res;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t -- > 0){
            long n = sc.nextLong();

            int k = level(n);
            long ans = n / power(2, k + 1);

            System.out.println(ans);
        }
    }
}
