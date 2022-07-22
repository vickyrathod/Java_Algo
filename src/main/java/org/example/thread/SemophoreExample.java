package org.example.thread;

import java.util.concurrent.Semaphore;

public class SemophoreExample {
    static Semaphore se = new Semaphore(4);

    public static void main(String args[]) throws InterruptedException {
        for(int i = 0; i < 49; i ++) {
            // to create 4 thread at a time
            // standard example of thread pool
            se.acquire();
            Thread t = new Thread(() ->{
                try {
                    System.out.println(Thread.currentThread() + "current count " + Thread.activeCount());
                    Thread.sleep(400);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    se.release();
                }
            }, i+"");
            t.start();
        }
    }
}
