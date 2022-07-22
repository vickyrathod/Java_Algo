package org.example.stack;

import java.util.Stack;

public class MaxArray {
    static int[] getLeftMaxArray(int[] arr){
        int[] ans = new int[arr.length];
        int n = arr.length;
        Stack<Integer> st = new Stack<>();

        st.push(n-1);

        for(int i = n-2; i >= 0; i --){
            while(!st.isEmpty() && arr[i] >= arr[st.peek()]) {
                int x = st.pop();
                ans[x] = i;
            }
            st.push(i);
        }

        while(!st.empty()) {
            int x = st.pop();
            ans[x] = -1;
        }
        return ans;
    }

    static int[] getRightMaxArray(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];

        Stack<Integer> st = new Stack<>();

        st.push(0);

        for(int i = 1; i < n; i ++){
            while(!st.isEmpty() && arr[i] >= arr[st.peek()]) {
                int x = st.pop();
                ans[x] = i;
            }
            st.push(i);
        }
        while(!st.empty()) {
            int x = st.pop();
            ans[x] = -1;
        }
        return ans;
    }
}
