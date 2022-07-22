package org.example.hackerearth.junecercircute;

import java.util.Scanner;

public class NumberOfTriangles {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while(t -- > 0) {
            int n = sc.nextInt();
            int b1 = sc.nextInt();
            int b2 = sc.nextInt();
            long total = (n - 2) + (n - 4) + ((long)n - 2) * (n - 3);

            if(Math.abs(b2 - b1) == 1)
                total = total - (3 * n - 8);
            else if(Math.abs(b2 - b1) == 2)
                total = total - (4 * n - 11);

            else
                total = total - (4 * n - 10);

            System.out.println(total);
        }
    }
}
