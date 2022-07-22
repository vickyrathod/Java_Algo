package org.example.thread.divideandconcur.test;

import org.example.thread.divideandconcur.MergeSortMultipleThread;
import org.example.thread.divideandconcur.MergeSortSingleThread;
import org.example.thread.divideandconcur.QuickSortMutliThreaing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class QuickSortSpeedUpTest {

    public static void main(String args[]){
        Long start = System.currentTimeMillis();
        long time_sequential = 0;
        long time_parallel = 0;

        //ten loops for sequential merge sort
        for(int j = 0; j < 10; j ++){

            Random random = new Random();
            int n = 100_000_0;
            int[] arr = new int[n + 1];

            for(int i = 0; i < n; i ++)
                arr[i] = random.nextInt(100_000_0);

            MergeSortSingleThread.mergeSort(0, n, arr);
        }
        time_sequential = System.currentTimeMillis() - start;

        //Reset start
        start = System.currentTimeMillis();

        // Ten loops of parallel execution
        for(int j = 0; j < 10; j ++){

            Random random = new Random();
            int n = 100_000_0;
            int[] arr = new int[n + 1];

            for(int i = 0; i < n; i++)
                arr[i] = random.nextInt(100_000_0);

            ForkJoinPool pool = ForkJoinPool.commonPool();

            QuickSortMutliThreaing sort = new QuickSortMutliThreaing(0, n - 1, arr);
            pool.invoke(sort);
        }
        time_parallel = System.currentTimeMillis() - start;

        // Total speed up
        double speedup = (double) time_sequential/time_parallel;

        System.out.println("The total speedup is : " + speedup);
        System.out.println("The efficiency is : " + (speedup/Runtime.getRuntime().availableProcessors())* 100);
    }
}
