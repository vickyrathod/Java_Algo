package org.example.codechef;

import java.util.Scanner;

import static java.lang.Long.min;

public class TTUPLE2 {
    public static void main (String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t -- > 0){
            long[] arr = new long[3];
            long[] xyz = new long[3];

            arr[0] = sc.nextInt();
            arr[1] = sc.nextInt();
            arr[2] = sc.nextInt();

            xyz[0] = sc.nextInt();
            xyz[1] = sc.nextInt();
            xyz[2] = sc.nextInt();

            System.out.println(solve(arr, xyz));
        }
    }

    public static long solve(long[] arr, long[] xyz) {
        long ans = 3;
        ans = min(ans, solveOne(arr[0], xyz[0]) + solveOne(arr[1], xyz[1]) + solveOne(arr[2], xyz[2]));
        ans = min(ans, solveTwo(arr[0], xyz[0], arr[1], xyz[1]) + solveOne(arr[2], xyz[2]));
        ans = min(ans, solveTwo(arr[0], xyz[0], arr[2], xyz[2]) + solveOne(arr[1], xyz[1]));
        ans = min(ans, solveTwo(arr[1], xyz[1], arr[2], xyz[2]) + solveOne(arr[0], xyz[0]));

        ans = min(solveThreeOneStep(arr, xyz) , ans);

        if(ans > 2 && checkForTwoStep(arr, xyz))
            ans = min(ans,2);

        return ans;
    }

    public static int solveOne(long a, long b){
        if(a == b)
            return 0;
        return 1;
    }

    public static int solveTwo(long a1, long b1, long a2, long b2) {

        int ans = (int) min(solveOne(a2  + (b1 - a1), b2) + 1, solveOne(a2 , b2) + solveOne(a1 , b1));

        if((a1 != 0 && a2 != 0)) {
            long q = b1/a1;
            ans = (int) min(solveOne(q * a1, b1) + solveOne(q * a2, b2) + 1, ans);
        }

        return ans;
    }

    public static int solveThreeOneStep(long[] a, long[] b) {
        int ans = solveOne(a[0] , b[0]) + solveOne(a[1] , b[1]) + solveOne(a[2] , b[2]);

        for(int i = 0; i < 3; i ++) {
            long d = b[i] - a[i];
            int n = (i + 1) % 3;
            int m = (i + 2) % 3;
            ans = (int) min(solveOne(a[n] + d, b[n]) + solveOne(a[m] + d, b[m]) + 1, ans);
            ans = (int) min(solveTwo(a[n] + d, b[n],a[m] + d, b[m]) + 1, ans);

            if(a[i] != 0) {
                long q = b[i] / a[i];
                int of = solveOne(q * a[i], b[i]);
                ans = (int) min( of + solveOne(q * a[n], b[n]) + solveOne(q * a[m], b[m]) + 1, ans);
                ans = (int) min(of + solveTwo(a[n] * q, b[n],a[m] * q, b[m]) + 1, ans);
            }
        }

        return ans;
    }

    public static boolean checkForTwoStep(long[] arr, long[] xyz) {
        long[] dn = new long[3];
        dn[0] = xyz[0] - xyz[1];
        dn[1] = xyz[1] - xyz[2];
        dn[2] = xyz[2] - xyz[0];

        long[] dd = new long[3];
        dd[0] = arr[0] - arr[1];
        dd[1] = arr[1] - arr[2];
        dd[2] = arr[2] - arr[0];

        long x = 0;
        for(int i = 0; i < 3; i ++) {
            if(dd[i] == 0){
                if(dn[i] != 0)
                    return false;

                // Two Overlaping lines
                int j = (i - 1 + 3) % 3;
                // Check for other
                if(dd[j] == 0) {
                    if(dn[j] != 0)
                        return false;
                    // all three overlaping
                    return true;
                } else {
                    x = dn[j] / dd[j];
                }
            } else
                x = dn[i] / dd[i];
        }

        // Add multiply

        if(dn[0] != (long)dd[0] * x || dn[1] != (long)dd[1] * x || dn[2] != (long)dd[2] * x)
            return false;

        long y = (long) (xyz[0] - (long)arr[0] * x);

        if(arr[1] * (long)x + y != xyz[1] || (long)arr[2] * x + y != xyz[2])
            return false;
        return true;
    }
}
