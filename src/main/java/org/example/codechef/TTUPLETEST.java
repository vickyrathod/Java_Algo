package org.example.codechef;

import java.util.Random;

public class TTUPLETEST {

    public static void main(String args[]){

        int t= 0;
        int ans = 2;
        boolean f = true;
        while(t < 200000) {
            long[] arr = new long[3];
            long[] xyz = new long[3];

            long k = getNonZeroInt() % 1000_000_00;
            long l = getNonZeroInt() % 1000_000_00;
            arr[0] = getInt();
            arr[1] = getInt();
            arr[2] = getInt();

            xyz[0] = getInt();
            xyz[1] = getInt();
            xyz[2] = getInt();

           int ans1 = (int) TTUPLE2.solve(arr, xyz);
           int ans2 = (int) TTUPLE.solveThree(arr, xyz, 0);
           int ans3 = (int) TestFour.solveThree(arr, xyz, 0);
            if(ans2 > ans3) {
                System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);
                System.out.println(xyz[0] + " " + xyz[1] + " " + xyz[2]);
                System.out.println(ans2 + " " + ans3);
            }
            t ++;
        }
        System.out.println("found at : " + t);
    }
    static int getNonZeroInt() {
        Random re = new Random();

//        while(true) {
            int r = re.nextInt();
//            if(r != 0)
                return r;
       // }
    }

    static int getInt() {
        Random re = new Random();
        return re.nextInt() % 11;
    }
}
