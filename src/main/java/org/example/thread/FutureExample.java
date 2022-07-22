package org.example.thread;

import java.util.concurrent.*;

class Adder implements Callable<Integer> {
    int a = 7;
    int b = 19;

    public Adder(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(300);
        return a + b;
    }
}

public class FutureExample {
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        Adder adder = new Adder(3, 4);
        ExecutorService pool = Executors.newSingleThreadExecutor();
        Future result = pool.submit(adder);
        System.out.println("Doing own work......");
        System.out.println("Waiting for adder...." + result.get());
        pool.shutdown();
    }
}
