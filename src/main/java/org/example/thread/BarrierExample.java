package org.example.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The barrier is used for count base waiting
 * if set a want to wait all set b threads to be done first
 *
 *
 * If the multiplication to be done before addition
 * we can use barrier
 *
 *
 * always try to use barrier than multiple joins
 */

public class BarrierExample {
    //to make Three Threads to be joined at a time
    static CyclicBarrier cb = new CyclicBarrier(3);
    static AtomicInteger at = new AtomicInteger(1);
    public static void add(){
        try {
            cb.await();
        } catch(InterruptedException e) {
            e.printStackTrace();
        } catch(BrokenBarrierException e) {
            e.printStackTrace();
        }
        at.addAndGet(2);
    }

    public static void addAndMul(){
        at.updateAndGet(x -> 2 * x);
        add();
    }
    public static void main(String args[]) throws InterruptedException {
        Thread t1 = new Thread(BarrierExample::addAndMul);
        Thread t3 = new Thread(BarrierExample::addAndMul);
        Thread t2 = new Thread(BarrierExample::addAndMul);
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(at.get());
    }
}
