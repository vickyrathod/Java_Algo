package org.example.thread;

public class SyncWithDifferentObjectSameClass {
    static int countInt = 0;

    static class Increamenter implements Runnable {
        void increment() {
            // Don't use this as it is different object
            synchronized(Increamenter.class) {
                countInt++;
            }
        }
        @Override
        public void run() {
            for(int i = 0; i < 10_000_000; i++)
                increment();
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Thread t1 = new Thread(new SyncWithDifferentObjectSameClass.Increamenter());
        Thread t2 = new Thread(new SyncWithDifferentObjectSameClass.Increamenter());

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(countInt);
    }
}
