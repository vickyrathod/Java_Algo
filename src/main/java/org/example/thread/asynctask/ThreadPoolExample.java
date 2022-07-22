package org.example.thread.asynctask;

import java.util.concurrent.*;
public class ThreadPoolExample {

    public static void run(){
        System.out.println(Thread.currentThread());
    }

    // Runing all Threads with executor
    public static void main(String args[]){
        int cpuNo = Runtime.getRuntime().availableProcessors();
        // creating thread pool
        ExecutorService pool  = Executors.newFixedThreadPool(cpuNo);

        for(int i = 0; i < 200; i++) pool.submit(new Thread(ThreadPoolExample::run));

        pool.shutdown();
    }
}
