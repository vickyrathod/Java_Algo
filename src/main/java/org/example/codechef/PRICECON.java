package org.example.codechef;

import java.util.Scanner;

public class PRICECON {

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t -- > 0){
            long ans = 0;
            int n = sc.nextInt();
            int k = sc.nextInt();
            int x;
            for(int i = 0; i < n; i ++){
                x = sc.nextInt();
                if(x > k)
                    ans += x - k;
            }

            System.out.println(ans);
        }
    }
}
