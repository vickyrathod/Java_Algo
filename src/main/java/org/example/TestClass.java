package org.example;

import java.util.Scanner;

public class TestClass {
        static int[] fac = new int[999983 + 1];
        static int[] inv = new int[999983 + 1];
        static int p = 999983;

        static int power(int x, int y, int p)
        {
            int res = 1;
            x = x % p;
            while (y > 0)
            {
                if (y % 2 == 1)
                    res = (res * x) % p;

                y = y >> 1; // y = y/2
                x = (x * x) % p;
            }

            return res;
        }

        static void modInverse()
        {
            for (int i = 1 ;i < 999984; i++)
                inv[i] = (power(fac[i], p-2, p) + p ) % p;
        }

        static void fact(){
            for (int i = 1 ;i < 999984; i++)
                fac[i] = (fac[i-1] * i) % p;
        }

        static int nCrModPFermat(int n, int r)
        {

            // Base case
            if (r == 0)
                return 1;

            return (((fac[n]*inv[r])
                    % p )* inv[n - r]
                    % p) % p;
        }

        public static void main(String args[]) {
            Scanner sc = new Scanner(System.in);
            fac[0] = 1;
            fact();
            modInverse();

            int t = sc.nextInt();

            while(t -- > 0) {
                long a = sc.nextLong();
                long b = sc.nextLong();
                if(a > b)
                    System.out.println(0);
                else
                    System.out.println(nCrModPFermat((int)((a + b) % p), (int)(b % p)) - nCrModPFermat((int)((a + b) % p), (int)((b + 1) % p)));
            }
        }
}
