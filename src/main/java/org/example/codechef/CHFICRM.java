package org.example.codechef;

import java.util.Scanner;

public class CHFICRM {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while(t -- > 0) {
            int five = 0;
            int ten = 0;
            int fifteen = 0;

            int n = sc.nextInt();
            boolean can_serve = true;

            for(int i = 0; i < n; i ++) {
                int x = sc.nextInt();
                int y =  x - 5;
                if(y == 0)
                    five ++;
                else if(y == 5) {
                    if(five == 0)
                        can_serve = false;
                    else {
                        five --;
                        ten++;
                    }
                }
                else if(y == 10) {
                    if(ten < 1 && five < 2)
                        can_serve = false;
                    else {
                        if(ten >= 1)
                            ten --;
                        else
                            five -= 2;
                        fifteen++;
                    }
                }
            }

            if(can_serve)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
