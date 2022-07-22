package org.example.codechef;

import static java.lang.Long.min;

public class TestFour {

    public static int solveOne(long a, long b){
        if(a == b)
            return 0;
        return 1;
    }

    public static int solveThree(long[] a, long[] b, int level) {
        int ans = Integer.min(solveOne(a[0] , b[0]) + solveOne(a[1] , b[1]) + solveOne(a[2] , b[2]), 3);
        if(ans == 0)
            return 0;
        if(level == 2)
            return 3;

        for(int i = 0; i < 3; i ++) {
            int n = (i + 1) % 3;
            int m = (i + 2) % 3;

            ans = Integer.min(ans, singleAddMul(a, b, i, n, m, level));

            ans = Integer.min(ans, pairAddMul(a, b, i, n, m, level));
        }

        return ans;
    }

    private static int pairAddMul(long[] a, long[] b, int i, int n, int m, int level) {
        int ans = 3;
        if(noSolution(a[n] - a[m], b[n] - b[m]))
            return ans;

        if(a[n] - a[m] == 0 && b[n] - b[m] == 0)
            return 2;

        long q =  (b[n] - b[m]) / (a[n] - a[m]);
        long r =  b[n] - a[n] * q;

        // Add
        if(r != 0)
            ans = Integer.min(ans, allPossiblePairAdd(a, b, r, level));

        // Multiply
        if(q != 1)
            ans = Integer.min(ans, allPossiblePairMul(a, b, q, level));

        return ans;
    }

    private static int singleAddMul(long[] a, long[] b, int i, int n, int m, int level) {
        long q = 1, r;
        //For single
        if(a[i] != 0)
            q = b[i] / a[i];
        r = b[i] - a[i];
        int ans = 3;
        // add
        if(r != 0)
            ans = Integer.min(ans, allPossiblePairAdd(a, b, r, level));

        // mul
        if(a[i] != 0 && q != 1)
            ans = Integer.min(ans, allPossiblePairMul(a, b, q, level));

        return ans;
    }

    private static boolean noSolution(long a, long b) {
        if(a != 0)
            return false;
        if(b == 0)
            return false;

        return true;
    }

    /*
{ a }
{ b }
{ a b }
{ c }
{ a c }
{ b c }
{ a b c }
 */
    private static int allPossiblePairAdd(long[] a, long[] b, long r, int level) {
        a[0] = a[0] + r;
        int ans = Integer.min(solveThree(a, b,level + 1) + 1, 3);
        a[0] = a[0] - r;

        a[1] = a[1] + r;
        ans = Integer.min(solveThree(a, b,level + 1) + 1, ans);
        a[1] = a[1] - r;

        a[2] = a[2] + r;
        ans = Integer.min(solveThree(a, b,level + 1) + 1, ans);
        a[2] = a[2] - r;

        a[0] = a[0] + r;
        a[1] = a[1] + r;
        ans = Integer.min(solveThree(a, b,level + 1) + 1, ans);
        a[0] = a[0] - r;
        a[1] = a[1] - r;

        a[0] = a[0] + r;
        a[2] = a[2] + r;
        ans = Integer.min(solveThree(a, b,level + 1) + 1, ans);
        a[0] = a[0] - r;
        a[2] = a[2] - r;

        a[1] = a[1] + r;
        a[2] = a[2] + r;
        ans = Integer.min(solveThree(a, b,level + 1) + 1, ans);
        a[1] = a[1] - r;
        a[2] = a[2] - r;

        a[0] = a[0] + r;
        a[1] = a[1] + r;
        a[2] = a[2] + r;
        ans = Integer.min(solveThree(a, b,level + 1) + 1, ans);
        a[0] = a[0] - r;
        a[1] = a[1] - r;
        a[2] = a[2] - r;

        return ans;
    }

    private static int allPossiblePairMul(long[] a, long[] b, long q, int level) {

        long temp1 = a[0];
        long temp2 = a[1];
        long temp3 = a[2];

        a[0] = a[0] * q;
        int ans = Integer.min(solveThree(a, b,level + 1) + 1, 3);
        a[0] = temp1;

        a[1] = a[1] * q;
        ans = Integer.min(solveThree(a, b,level + 1) + 1, ans);
        a[1] = temp2;

        a[2] = a[2] * q;
        ans = Integer.min(solveThree(a, b,level + 1) + 1, ans);
        a[2] = temp3;

        a[0] = a[0] * q;
        a[1] = a[1] * q;
        ans = Integer.min(solveThree(a, b,level + 1) + 1, ans);
        a[0] = temp1;
        a[1] = temp2;

        a[0] = a[0] * q;
        a[2] = a[2] * q;
        ans = Integer.min(solveThree(a, b,level + 1) + 1, ans);
        a[0] = temp1;
        a[2] = temp3;

        a[1] = a[1] * q;
        a[2] = a[2] * q;
        ans = Integer.min(solveThree(a, b,level + 1) + 1, ans);
        a[1] = temp2;
        a[2] = temp3;

        a[0] = a[0] * q;
        a[1] = a[1] * q;
        a[2] = a[2] * q;
        ans = Integer.min(solveThree(a, b,level + 1) + 1, ans);
        a[0] = temp1;
        a[1] = temp2;
        a[2] = temp3;

        return ans;
    }
}
