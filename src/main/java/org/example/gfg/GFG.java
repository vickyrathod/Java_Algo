package org.example.gfg;/*package whatever //do not write package name here */
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class GFG {
    // start of code
    public static void main(String args[]){
        // Any random integers
        Random random = new Random();
        int n = 10;
        int[] arr = new int[n];
        for(int i = 0; i < n; i ++)
            arr[i] = random.nextInt(100_000_0);

        // Forkjoin ThreadPool to keep thread creation as per resources
        ForkJoinPool pool = ForkJoinPool.commonPool();

        // Start the first thread in fork join pool for range 0, n-1
        pool.invoke(new QuickSortMutliThreading(0, n - 1, arr));

        // Shut down the pool or program will not stop executing
        pool.shutdown();

        // Print shorted elements
        for(int i = 0; i < n; i ++)
            System.out.print(arr[i] + " ");
    }
}

class QuickSortMutliThreading extends RecursiveTask<Integer> {

    int start, end;
    int[] arr;

    /**
     * Finding random pivote and partion array on pivote.
     * There are many different partinaning algorithmn.
     * @param start
     * @param end
     * @param arr
     * @return
     */
    private int partion(int start, int end, int[] arr){
        int i = start, j = end;
        // Decide random pivot
        int pivote = new Random().nextInt(j - i) + i;

        // Swap the pivote with end element of array;
        int t = arr[j];
        arr[j] = arr[pivote];
        arr[pivote] = t;
        j --;

        // Start partioning
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

        // Swap pivote to its correct position
        t = arr[j + 1];
        arr[j + 1] = arr[end];
        arr[end] = t;
        return j + 1;
    }

    public QuickSortMutliThreading(int start, int end, int[] arr) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        // Base case
        if(start >= end)
            return null;
        // Find partion
        int p = partion(start, end, arr);

        //Divide array
        QuickSortMutliThreading left = new QuickSortMutliThreading(start, p - 1, arr);
        QuickSortMutliThreading right = new QuickSortMutliThreading(p + 1, end, arr);

        // Left subproblem as separate thread
        left.fork();
        right.compute();

        //wait untill left thread complete
        left.join();

        // We dont want anything as return
        return null;
    }
}