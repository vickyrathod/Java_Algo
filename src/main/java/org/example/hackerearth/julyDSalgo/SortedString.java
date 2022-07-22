package org.example.hackerearth.julyDSalgo;

import java.util.*;
import java.io.*;

public class SortedString {

    private static int strictUpperBound(int x, ArrayList<Integer> arr) {
        int l = 0;
        int h = arr.size() - 1;

        int ans = arr.size();

        while(l <= h){
            int m = (l + h) / 2;

            if(x >= arr.get(m)) {
                l = m + 1;
                continue;
            }
            ans = m;
            h = m - 1;
        }

        return ans;
    }

    public static void main(String args[]) throws IOException {
        //testStrictUpperBound();
        Scan sc = new Scan();
        int n = sc.scanInt();
        int[] arr = new int[n];
        int[] cs = new int[n];
        ArrayList<Integer> aList = new ArrayList<>();
        ArrayList<Integer> cCount = new ArrayList<>();
        String str = sc.scanString();

        int count = 0;
        for(int i = 0; i < n; i++) {
            cs[i] = count;
            if(str.charAt(i) == 'c') {
                count++;
            }
            if(str.charAt(i) == 'a') {
                aList.add(i);
                cCount.add(count);
            }
        }

        int mulfactor = 0;

        int ans = 0;
        int mod = 1000000007;

        for(int i = 0; i < str.length(); i ++) {
            mulfactor ++;
            if(str.charAt(i) == 'a' || str.charAt(i) == 'c') {
                ans = (ans + mulfactor * mulfactor + mulfactor * findAns(i, aList, cCount, cs));
            }
        }
    }

    private static int findAns(int i, ArrayList<Integer> aList, ArrayList<Integer> cCount, int[] cs) {
        int startJ = strictUpperBound(i, aList);

        int a = 0;
        while(startJ < cs.length) {
            int c = cCount.get(startJ) - cs[i];
            a ++;
            if(a > startJ)
                a = 6;
        }

        return 0;
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
