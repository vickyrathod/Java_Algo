package org.example.thread;

import java.util.HashMap;
import java.util.Map;

public class SyncHashMap {
    //Shared Hashmap
    static Map<Integer, Integer> hm = new HashMap<>();

    static class Increamentor implements Runnable {

        void increment(){
            synchronized(hm) {
                    hm.put(0, hm.get(0) + 1);
            }
        }

        @Override
        public void run() {
            for(int i = 0; i < 10_000_000; i++)
                increment();
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Thread t1 = new Thread(new SyncHashMap.Increamentor());
        Thread t2 = new Thread(new SyncHashMap.Increamentor());

        hm.put(0, 0);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(hm.get(0));
    }
}
