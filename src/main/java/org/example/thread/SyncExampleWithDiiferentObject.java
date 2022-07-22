package org.example.thread;

public class SyncExampleWithDiiferentObject {
    static int countInt = 0;

    static class Increamenter implements Runnable {
        synchronized void increment() {
            countInt++;
        }
        @Override
        public void run() {
            for(int i = 0; i < 10_000_000; i++)
                increment();
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Thread t1 = new Thread(new Increamenter());
        Thread t2 = new Thread(new Increamenter());

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(countInt);
    }
}
