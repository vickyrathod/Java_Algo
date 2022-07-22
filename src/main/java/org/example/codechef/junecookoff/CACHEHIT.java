package org.example.codechef.junecookoff;

import java.util.Scanner;

public class CACHEHIT {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while(t -- > 0) {
            int[] arr = new int[100000];
            int n = sc.nextInt();
            int b = sc.nextInt();
            int m = sc.nextInt();

            int k = 0;
            int x = b;
            for(int i = 0; i < n; i ++) {
                arr[i] = k;
                x --;
                if(x == 0) {
                    x = b;
                    k ++;
                }
            }

            int prev = -1;
            int ans = 0;
            for(int i = 0; i < m; i ++) {
                int q = sc.nextInt();
                if(arr[q] == prev)
                    continue;
                prev = arr[q];
                ans ++;
            }

            System.out.println(ans);
        }
    }
}
