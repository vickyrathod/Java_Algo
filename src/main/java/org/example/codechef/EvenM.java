package org.example.codechef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class ValueSupplier {
    int odd = -1;
    int even = 0;

    public int getEven() {
        even += 2;
        return even;
    }

    public int getOdd() {
        odd += 2;
        return odd;
    }
}

public class EvenM {

    int n;

    public EvenM(int n){
        this.n = n;
    }

    public int[][] find() {
        ValueSupplier valueSupplier = new ValueSupplier();
        int[][] ans = new int[n][n];
        boolean isOdd = true;
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < n; j ++) {
                if(isOdd)
                    ans[i][j] = valueSupplier.getOdd();
                else
                    ans[i][j] = valueSupplier.getEven();

                isOdd = !isOdd;
            }
            if(n % 2 == 0)
                isOdd = !isOdd;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        while(t -- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            EvenM evenM = new EvenM(n);
            int[][] arr = evenM.find();

            for(int i = 0; i < arr.length; i ++) {
                for(int j = 0; j < arr.length; j++)
                    System.out.print(arr[i][j] + " ");
                System.out.println();
            }
        }
    }
}
