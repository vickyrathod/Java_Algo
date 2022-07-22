package org.example.codechef;

import java.util.Scanner;
import java.util.Stack;

public class XYSTR {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t -- > 0) {
            String str = sc.next();
            Stack<Character> st = new Stack<>();

            int ans = 0;
            for(int i = 0; i < str.length(); i ++){
                if(st.isEmpty() || st.peek() == str.charAt(i)) {
                    st.push(str.charAt(i));
                    continue;
                }

                while(!st.isEmpty())
                    st.pop();
                ans ++;
            }

            System.out.println(ans);
        }
    }
}
