package org.example.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockTest {

    static class ShoppingList implements Runnable {
        //Shared creatical section
        static int totalItem = 0;
        // Lock always need to be shared
        static Lock re = new ReentrantLock();

        @Override
        public void run() {
            int itemToadd = 0;
            while(totalItem < 20) {
                if(itemToadd > 0 && re.tryLock()) {
                    totalItem += itemToadd;
                    System.out.println("adding item by Thread: " + Thread.currentThread() + " count : " + totalItem + " item: " + itemToadd);
                    itemToadd = 0;
                    re.unlock();
                } else {
                    itemToadd ++;
                    try {
                        Thread.sleep(100);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Thread t1 = new Thread(new ShoppingList());
        Thread t2 = new Thread(new ShoppingList());

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
