package org.example.thread.divideandconcur;

import java.util.Random;

public class QuickSortSequential {

    /**
     * Finding random pivote and partion array on pivote
     * @param start
     * @param end
     * @param arr
     * @return
     */
    private int partion(int start, int end, int[] arr){
        int i = start, j = end;
        int pivote = new Random().nextInt(j - i) + i;

        // Swap the pivote with end element of array;
        int t = arr[j];
        arr[j] = arr[pivote];
        arr[pivote] = t;
        j --;

        // Start partionaning
        while(i <= j){
            if(arr[i] <= arr[end]){
                i ++;
                continue;
            }
            if(arr[j] >= arr[end]) {
                j --;
                continue;
            }

            t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
            j --;
            i ++;
        }

        // swap pivote to its correct position
        t = arr[j + 1];
        arr[j + 1] = arr[end];
        arr[end] = t;
        return j + 1;
    }

    public void quickSort(int start, int end, int[] arr){
        // Base case
        if(start >= end)
            return;
        // Find partion
        int p = partion(start, end, arr);

        quickSort(start, p - 1, arr);
        quickSort(p + 1, end, arr);
    }

    public static void main(String args[]){
        QuickSortSequential sort = new QuickSortSequential();
        Random random = new Random();
        int n = 100_000_0;
        int[] arr = new int[n];

        for(int i = 0; i < n; i ++)
            arr[i] = random.nextInt(100_000_0);
        sort.quickSort(0, n - 1, arr);

        for(int i = 0; i < n; i ++)
            System.out.print(arr[i] + " ");
    }
}
