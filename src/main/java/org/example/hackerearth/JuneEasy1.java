package org.example.hackerearth;

import java.util.Scanner;

public class JuneEasy1 {

    public static void main(String args[]){
        Scanner sc =  new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();



        if(m > n -1)
            System.out.println(n);
        else
            System.out.println(m);
    }
}
