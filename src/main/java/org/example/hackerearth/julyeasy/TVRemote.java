package org.example.hackerearth.julyeasy;

import java.util.*;
import java.io.*;

public class TVRemote {
    static int[] fact = new int[100001];
    static int[] inv = new int[100001];
    static final int mod = 100_000_000_7;

    static int powerMod(long x, long y) {
        // Initialize result
        long res = 1;

        // Update x if it is more
        // than or equal to p
        x = x % mod;

        if (x == 0) return 0; // In case x is divisible by p;

        while (y > 0)
        {
            // If y is odd, multiply x
            // with result
            if((y & 1)==1)
                res = (res * x) % mod;

            // y must be even now
            // y = y / 2
            y = y >> 1;
            x = (x * x) % mod;
        }
        return (int)res;
    }

    //pre computation
    public static void invFinder(){
        for(int i = 0; i < 100001; i ++ )
            inv[i] = powerMod(fact[i], mod - 2);
    }


    //pre computation
    public static void factFinder(){
        fact[0] = 1;
        for(int i = 1; i < 100001; i ++ )
            fact[i] = (int)(((long)fact[i - 1] * i) % mod);
    }


    public static void main(String args[]) throws IOException {
        Scan sc = new Scan();
        int t = sc.scanInt();
        factFinder();
        invFinder();

        while(t-- > 0) {
            int n = sc.scanInt();
            long sum = getSum(n);

            System.out.println(sum);
        }
    }

    public static long getSum(int n) {
        factFinder();
        invFinder();
        long sum = 0;
        for(int i = 0; i <= n; i ++) {
            int e = n - i;
            int box = n - i + 1;
            int balls = i;

            int m =  box + balls - 1;
            int r = box - 1;

            if(e % 2 == 0) {
                long z = 1;
                if(r >= 0)
                    z = ((((long) fact[m] * inv[m - r]) % mod) * inv[r]) % mod;
                long boxper = ((fact[e] * (long)inv[e/2]) % mod) * inv[e/2] % mod;
                sum = (sum + ((boxper * z)) % mod) % mod;
            }
        }
        return sum;
    }
}

class Scan {
    private byte[] buf = new byte[1024];
    private int index;
    private InputStream in;
    private int total;

    public Scan() {
        in = System.in;
    }

    public int scan() throws IOException {
        if(total < 0)
            throw new InputMismatchException();
        if(index >= total) {
            index = 0;
            total = in.read(buf);
            if(total <= 0)
                return -1;
        }
        return buf[index++];
    }

    public int scanInt() throws IOException {
        int integer = 0;
        int n = scan();
        while(isWhiteSpace(n))
            n = scan();
        int neg = 1;
        if(n == '-') {
            neg = -1;
            n = scan();
        }
        while(!isWhiteSpace(n)) {
            if(n >= '0' && n <= '9') {
                integer *= 10;
                integer += n - '0';
                n = scan();
            } else throw new InputMismatchException();
        }
        return neg * integer;
    }

    public double scanDouble() throws IOException {
        double doub = 0;
        int n = scan();
        while(isWhiteSpace(n))
            n = scan();
        int neg = 1;
        if(n == '-') {
            neg = -1;
            n = scan();
        }
        while(!isWhiteSpace(n) && n != '.') {
            if(n >= '0' && n <= '9') {
                doub *= 10;
                doub += n - '0';
                n = scan();
            } else throw new InputMismatchException();
        }
        if(n == '.') {
            n = scan();
            double temp = 1;
            while(!isWhiteSpace(n)) {
                if(n >= '0' && n <= '9') {
                    temp /= 10;
                    doub += (n - '0') * temp;
                    n = scan();
                } else throw new InputMismatchException();
            }
        }
        return doub * neg;
    }

    public String scanString() throws IOException {
        StringBuilder sb = new StringBuilder();
        int n = scan();
        while(isWhiteSpace(n))
            n = scan();
        while(!isWhiteSpace(n)) {
            sb.append((char) n);
            n = scan();
        }
        return sb.toString();
    }

    private boolean isWhiteSpace(int n) {
        if(n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1)
            return true;
        return false;
    }
}

class Print {
    private final OutputStream out;

    /*public Print(OutputStream outputStream)
	{
		writer=new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
	}*/
    public Print() {
        this.out = System.out;
    }

    public void print(String str) throws IOException {
        //buf=str.getBytes();
        for(int i = 0; i < str.length(); i++) {
			/*if (i != 0)
			out.write(' ');*/
            out.write(str.charAt(i));
        }
    }

    public void printLine(String str) throws IOException {
        print(str);
        out.write('\n');
    }

    public void close() throws IOException {
        out.close();
    }
}
