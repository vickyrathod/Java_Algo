package org.example.thread.divideandconcur;

import java.util.Random;

/**
 * The merge sort is work on D&C
 * * Mergesort method is used for divide.
 * the merge method is for concur.
 */
public class MergeSortSingleThread {

    public static void mergeSort(int start, int end, int[] arr){
        // Base condition
        if(start == end)
            return;

        int mid = (start + end) / 2;

        //devide start
        // left call
        mergeSort(start, mid, arr);
        // right call
        mergeSort(mid + 1, end, arr);
        // divide end

        // concure
        // merge
        merge(start, end, mid, arr);
    }

    /**
     * This method can return array even
     *
     * @param start
     * @param end
     * @param mid
     * @param arr
     */
    private static void merge(int start, int end, int mid, int[] arr) {
        int[] temp = new int[end - start + 1];

        int l = start, r = mid + 1;
        int i = 0;
        while(l <= mid && r <= end){
            if(arr[l] > arr[r])
                temp[i ++] = arr[r ++];
            else
                temp[i ++] = arr[l ++];
        }

        while(l <= mid)
            temp[i ++] = arr[l ++];

        while(r <= end)
            temp[i ++] = arr[r ++];

        for(int j = 0, k = start; k <= end; k ++)
            arr[k] = temp[j ++];
    }

    public static void main(String args[]){
        Random random = new Random();
        int n = 100_000_0;
        int[] arr = new int[n + 1];

        for(int i = 0; i < n; i ++)
            arr[i] = random.nextInt(100_000_0);

        mergeSort(0, n, arr);

        for(int i = 0; i < n; i ++)
            System.out.print(arr[i] + " ");
    }
}
