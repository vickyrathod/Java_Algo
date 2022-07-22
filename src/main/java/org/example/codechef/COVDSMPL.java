package org.example.codechef;

import java.util.Scanner;

public class COVDSMPL {

    static Scanner sc;
    public static void main (String[] args) throws java.lang.Exception
    {
        sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t -- > 0){
            int n = sc.nextInt();
            int p = sc.nextInt();
            System.out.println("1 1 1 " + n + " " + n);
            int total_one = sc.nextInt();
            int[][] ans = new int[n][n];
            solve(ans, 0, 0, n - 1, n - 1, total_one, 0);

            System.out.println("2");

            for(int i = 0; i < n; i ++) {
                for(int j = 0; j < n; j++)
                    System.out.print(ans[i][j] + " ");
                System.out.println();
            }
            int x = sc.nextInt();
            if(x == -1)
                return;
        }
    }

    private static void solve(int[][] ans, int i, int j, int n, int m, int total_one, int ct) {
        if(i > n || j > m || total_one <= 0)
            return;
        // BaseCase
        if(total_one == (n - i + 1) * (m - j + 1)) {
            fillAllOnes(ans, i, j, n, m);
            return;
        }

        // devide by row
        if(i < n){
            System.out.println("1 " + (i + 1) + " " + (j + 1) + " " + (getpart(n + i, ct) + 1) + " " + (m + 1));
            int tmp = sc.nextInt();

            //recursive call
            solve(ans, i, j, getpart(n + i, ct), m, tmp, ct + 1);
            solve(ans, getpart(n + i, ct) + 1, j, n, m, total_one - tmp, ct + 1);
            return;
        } else if(j < m) {
            System.out.println("1 " + (i + 1) + " " + (j + 1) + " " + (n + 1) + " " + (getpart(m + j, ct) + 1));
            int tmp = sc.nextInt();

            //recursive call
            solve(ans, i, j, n, getpart(m + j, ct), tmp, ct + 1);
            solve(ans, i, getpart(m + j, ct) + 1, n, m, total_one - tmp, ct + 1);
            return;
        }

        ans[i][j] = 1;
    }

    private static int getpart(int n, int c){
        return c % 2 == 0 ? n / 3 : (2 * n) / 3;
    }

    private static void fillAllZeroes(int[][] ans, int i, int j, int n, int m) {
        for(; i <= n; i ++)
            for(; j <= m; j ++)
                ans[i][j] = 0;
    }

    private static void fillAllOnes(int[][] ans, int i, int j, int n, int m) {
        for(; i <= n; i ++)
            for(; j <= m; j ++)
                ans[i][j] = 1;
    }
}
