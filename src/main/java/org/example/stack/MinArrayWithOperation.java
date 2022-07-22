package org.example.stack;

import java.util.Stack;
import java.util.function.UnaryOperator;

public class MinArrayWithOperation {
    public static int[] getLeftArray(int[] arr, UnaryOperator<Integer> operator){

        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        st.add(n - 1);

        for(int i = n - 2; i >= 0; i --){
            while(!st.isEmpty() && arr[st.peek()] >= operator.apply(arr[i])){
                int x = st.pop();
                ans[x] = i;
            }

            st.add(i);
        }

        while(!st.isEmpty()){
            int x = st.pop();
            ans[x] = -1;
        }
        return ans;
    }

    public static int[] getRightArray(int[] arr, UnaryOperator<Integer> operator){

        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        st.add(0);

        for(int i = 1; i < n - 1; i ++){
            while(!st.isEmpty() && arr[st.peek()] >= operator.apply(arr[i])){
                int x = st.pop();
                ans[x] = i;
            }

            st.add(i);
        }

        while(!st.isEmpty()){
            int x = st.pop();
            ans[x] = -1;
        }

        return ans;
    }
}
