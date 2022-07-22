package org.example.codechef;

import static java.lang.Long.max;
import static java.lang.Long.min;
import static java.lang.Integer.min;

public class TTUPLE1 {

    public static long solve(long[] arr, long[] xyz) {
        long ans = 3;
        ans = min(solveThreeOneStep(arr, xyz) , ans);

        return ans;
    }

    public static int solveOne(long a, long b){
        if(a == b)
            return 0;
        return 1;
    }

    public static int solveTwo(long a1, long b1, long a2, long b2) {

       int ans = min(solveOne(a2  + (b1 - a1), b2) + 1, solveOne(a2 , b2) + solveOne(a1 , b1));

       if((a1 != 0 && a2 != 0)) {
            long q = b1/a1;
           ans = min(solveOne(q * a1, b1) + solveOne(q * a2, b2) + 1, ans);
        }

        return ans;
    }

    public static int solveThreeOneStep(long[] a, long[] b) {
        int ans = solveOne(a[0] , b[0]) + solveOne(a[1] , b[1]) + solveOne(a[2] , b[2]);


        for(int i = 0; i < 3; i ++) {
            int n = (i + 1) % 3;
            int m = (i + 2) % 3;

            //Without anything
            ans = min(solveOne(a[n] , b[n]) + solveOne(a[m] , b[m]) + solveOne(a[i], b[i]), ans);
            ans = min(solveTwo(a[n] , b[n],a[m] , b[m]) + solveOne(a[i], b[i]), ans);

            if(noSolution(a[n] - a[m], b[n] - b[m]))
                continue;

            if(a[n] - a[m] == 0 && b[n] - b[m] == 0) {
                ans = min(ans, solveTwo(a[i] , b[i],a[m] , b[m]));
                continue;
            }


            long q =  (b[n] - b[m]) / (a[n] - a[m]);
            long r =  b[n] - a[n] * q;

            int of = 2;

            if(q == 1)
                of --;
            if(r == 0)
                of --;

            ans = min(solveOne(a[n] * q + r, b[n]) + solveOne(a[m] * q + r, b[m]) + solveOne(a[i] * q + r, b[i]) + of, ans);
            ans = min(solveTwo(a[n] * q + r, b[n], a[m]  * q + r, b[m]) + solveOne(a[i] * q + r, b[i]) + of , ans);
            ans = min(solveTwo(a[n] * q + r, b[n], a[i] * q + r, b[i]) + solveOne(a[m]  * q + r, b[m]) + of , ans);
            ans = min(solveTwo(a[m]  * q + r, b[m], a[i] * q + r, b[i]) + solveOne(a[n] * q + r, b[n]) + of , ans);
        }

        return ans;
    }

    private static boolean noSolution(long a, long b) {
        if(a != 0)
            return false;
        if(b == 0)
            return false;

        return true;
    }
}
