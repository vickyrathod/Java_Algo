package org.example.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantLock {
    static int countInt = 0;
    //Used with recursive call to add lock and unlock multiple time
    static ReentrantLock re = new ReentrantLock();
    static class IncreamenterAndDecrementor implements Runnable {
        void increment() {
            re.lock();
            countInt++;
            decreament();
            re.unlock();
        }
        void decreament() {
            re.lock();
            countInt --;
            re.unlock();
        }
        @Override
        public void run() {
            for(int i = 0; i < 10_000_000; i++)
                increment();
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Thread t1 = new Thread(new ReEntrantLock.IncreamenterAndDecrementor());
        Thread t2 = new Thread(new ReEntrantLock.IncreamenterAndDecrementor());

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(countInt);
    }
}
