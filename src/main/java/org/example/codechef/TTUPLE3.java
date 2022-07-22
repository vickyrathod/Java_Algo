package org.example.codechef;

import static java.lang.Long.min;

public class TTUPLE3 {
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

        int ans = Integer.min(solveOne(a2  + (b1 - a1), b2) + 1, solveOne(a2 , b2) + solveOne(a1 , b1));
        ans = Integer.min(solveOne(a2 , b2) + 1, ans);

        if((a1 != 0 && a2 != 0)) {
            long q = b1/a1;
            ans = Integer.min(solveOne(q * a1, b1) + solveOne(q * a2, b2) + 1, ans);
            ans = Integer.min(solveOne(a1, b1) + solveOne(q * a2, b2) + 1, ans);
            ans = Integer.min(solveOne(q * a1, b1) + solveOne(a2, b2) + 1, ans);
        }

        return ans;
    }

    public static int solveThreeOneStep(long[] a, long[] b) {
        int ans = solveOne(a[0] , b[0]) + solveOne(a[1] , b[1]) + solveOne(a[2] , b[2]);

        for(int i = 0; i < 3; i ++) {
            int n = (i + 1) % 3;
            int m = (i + 2) % 3;

            ans = Integer.min(ans, singleAddMul(a, b, ans, i, n, m));

            ans = Integer.min(ans, pairAddMul(a, b, ans, i, n, m));
        }

        return ans;
    }

    private static int pairAddMul(long[] a, long[] b, int ans, int i, int n, int m) {
        if(noSolution(a[n] - a[m], b[n] - b[m]))
            return ans;

        if(a[n] - a[m] == 0 && b[n] - b[m] == 0) {
            ans = Integer.min(ans, solveTwo(a[i] , b[i], a[m] , b[m]));
            return ans;
        }

        long q =  (b[n] - b[m]) / (a[n] - a[m]);
        long r =  b[n] - a[n] * q;

        int of = 2;

        if(q == 1)
            of --;
        if(r == 0)
            of --;

        // Add
        if(r != 0) {
            ans = Integer.min(solveTwo(a[n] + r, b[n], a[m] + r, b[m]) + solveOne(a[i] + r, b[i]) + 1, ans);
            ans = Integer.min(solveTwo(a[n] , b[n], a[m] + r, b[m]) + solveOne(a[i] + r , b[i]) + 1, ans);
            ans = Integer.min(solveTwo(a[n] + r, b[n], a[m], b[m]) + solveOne(a[i] + r, b[i]) + 1, ans);
            ans = Integer.min(solveTwo(a[n], b[n], a[m], b[m]) + solveOne(a[i] + r, b[i]) + 1, ans);

            ans = Integer.min(solveTwo(a[n] + r, b[n], a[m] + r, b[m]) + solveOne(a[i], b[i]) + 1, ans);
            ans = Integer.min(solveTwo(a[n] , b[n], a[m] + r, b[m]) + solveOne(a[i] , b[i]) + 1, ans);
            ans = Integer.min(solveTwo(a[n] + r, b[n], a[m], b[m]) + solveOne(a[i], b[i]) + 1, ans);
            ans = Integer.min(solveTwo(a[n], b[n], a[m], b[m]) + solveOne(a[i], b[i]), ans);
        }

        // Multiply
        if(q != 1) {
            ans = Integer.min(solveTwo(a[n] * q, b[n], a[m] * q, b[m]) + solveOne(a[i] * q, b[i]) + 1, ans);
            ans = Integer.min(solveTwo(a[n], b[n], a[m] * q, b[m]) + solveOne(a[i] * q, b[i]) + 1, ans);
            ans = Integer.min(solveTwo(a[n] * q, b[n], a[m], b[m]) + solveOne(a[i] * q, b[i]) + 1, ans);
            ans = Integer.min(solveTwo(a[n], b[n], a[m], b[m]) + solveOne(a[i] * q, b[i]) + 1, ans);

            ans = Integer.min(solveTwo(a[n] * q, b[n], a[m] * q, b[m]) + solveOne(a[i], b[i]) + 1, ans);
            ans = Integer.min(solveTwo(a[n], b[n], a[m] * q, b[m]) + solveOne(a[i], b[i]) + 1, ans);
            ans = Integer.min(solveTwo(a[n] * q, b[n], a[m], b[m]) + solveOne(a[i], b[i]) + 1, ans);
            ans = Integer.min(solveTwo(a[n], b[n], a[m], b[m]) + solveOne(a[i], b[i]), ans);
        }

        // Add and Multiply
        ans = Integer.min(solveTwo(a[n] * q + r, b[n], a[m] * q + r, b[m]) + solveOne(a[i]  * q + r, b[i]) + of , ans);
        ans = Integer.min(solveTwo(a[n], b[n], a[m] * q + r, b[m]) + solveOne(a[i]  * q + r, b[i]) + of , ans);
        ans = Integer.min(solveTwo(a[n] * q + r, b[n], a[m], b[m]) + solveOne(a[i]  * q + r, b[i]) + of , ans);
        ans = Integer.min(solveTwo(a[n], b[n], a[m], b[m]) + solveOne(a[i]  * q + r, b[i]) + of , ans);

        ans = Integer.min(solveTwo(a[n] * q + r, b[n], a[m] * q + r, b[m]) + solveOne(a[i], b[i]) + of , ans);
        ans = Integer.min(solveTwo(a[n], b[n], a[m] * q + r, b[m]) + solveOne(a[i], b[i]) + of , ans);
        ans = Integer.min(solveTwo(a[n] * q + r, b[n], a[m], b[m]) + solveOne(a[i], b[i]) + of , ans);
        ans = Integer.min(solveTwo(a[n], b[n], a[m], b[m]) + solveOne(a[i], b[i]) , ans);

        return ans;
    }

    private static int singleAddMul(long[] a, long[] b, int ans, int i, int n, int m) {
        long q = 1, r;
        //For single
        if(a[i] != 0)
            q = b[i] / a[i];
        r = b[i] - a[i];

        // Nothing
        ans = Integer.min(solveTwo(a[n] , b[n], a[m] , b[m]) + solveOne(a[i], b[i]), ans);

        // add
        if(r != 0) {
            ans = Integer.min(solveTwo(a[n] + r, b[n], a[m] + r, b[m]) + 1, ans);
            ans = Integer.min(solveTwo(a[n] + r, b[n], a[m], b[m]) + 1, ans);
            ans = Integer.min(solveTwo(a[n], b[n], a[m] + r, b[m]) + 1, ans);
            ans = Integer.min(solveTwo(a[n], b[n], a[m], b[m]) + 1, ans);
        }

        // mul
        if(a[i] != 0 && q != 1) {
            ans = Integer.min(solveTwo(a[n] * q , b[n], a[m] * q , b[m]) + solveOne(a[i] * q, b[i]) + 1, ans);
            ans = Integer.min(solveTwo(a[n]  , b[n], a[m] * q , b[m]) + solveOne(a[i] * q, b[i]) + 1, ans);
            ans = Integer.min(solveTwo(a[n] * q , b[n], a[m]  , b[m]) + solveOne(a[i] * q, b[i]) + 1, ans);
            ans = Integer.min(solveTwo(a[n]  , b[n], a[m]  , b[m]) + solveOne(a[i] * q, b[i]) + 1, ans);
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
