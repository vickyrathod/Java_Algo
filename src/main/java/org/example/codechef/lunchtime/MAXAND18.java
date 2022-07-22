package org.example.codechef.lunchtime;

import javafx.util.Pair;

import java.util.*;
import java.io.*;

public class MAXAND18 {
    public static void main(String args[]) throws IOException {
        Scan sc = new Scan();
        int t = sc.scanInt();

        while(t-- > 0) {
            int n = sc.scanInt();
            int k = sc.scanInt();

            int[] arr = new int[n];

            for(int i = 0; i < n; i ++)
                arr[i] = sc.scanInt();

            int ans = getAns(k, arr);

            System.out.println(ans);
        }
    }

    public static int getAns(int k, int[] arr) {
        int ans = Integer.MAX_VALUE;
        int x = Integer.bitCount(ans);
        int remBit =  x - k;
        if(remBit < 0)
            ans = addKbit(arr, remBit, ans);
        else if(remBit > 0)
            ans = removeKbit(arr, remBit, ans);
        return ans;
    }

    private static int addKbit(int[] arr, int remBit, Integer key) {
        while(remBit != 0) {
            long max = Long.MIN_VALUE;
            int index = 0;
            for(int i = 0; i < 32; i ++) {
                if(((key >> i) & 1) == 1)
                    continue;
                long sum = 0;
                int temp = (key | (1 << i));

                for(int j = 0; j < arr.length; j ++)
                    sum += (temp & arr[j]);

                if(sum > max) {
                    max = sum;
                    index = i;
                }
            }

            key = (key & ~(1 << index));
            remBit --;
        }

        return key;
    }

    private static int removeKbit(int[] arr, int remBit, Integer key) {

        while(remBit != 0) {
            long max = Long.MIN_VALUE;
            int index = 0;
            for(int i = 0; i < 32; i ++) {
                if(((key >> i) & 1) == 0)
                    continue;
                long sum = 0;
                int temp = (key & ~(1 << i));

                for(int j = 0; j < arr.length; j ++)
                    sum += (temp & arr[j]);

                if(sum > max) {
                    max = sum;
                    index = i;
                }
            }

            key = (key & ~(1 << index));
            remBit --;
        }

        return key;
    }

    private static  int findMaxAns(int[] arr) {
        int ans = 0;
        for(int j = 0; j < arr.length; j++)
            ans = ans | arr[j];
        return ans;
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
