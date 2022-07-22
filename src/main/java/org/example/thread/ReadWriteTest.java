package org.example.thread;

import org.example.thread.util.SynchronizedHashMap;

import java.util.HashMap;

public class ReadWriteTest {
    // Shared read write stream
    static HashMap<Integer, Integer> hm = new SynchronizedHashMap<>();

    static void runImp() {
        for(int i = 0; i < 10_000_000; i ++)
            hm.compute(0, (k, v) -> {
                if(v == null || k == null)
                    return 1;
               return v + 1;
            });
    }
    public static void main(String args[]) throws InterruptedException {
            Thread t1 = new Thread(ReadWriteTest::runImp);
            Thread t2 = new Thread(ReadWriteTest::runImp);

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            System.out.println(hm.get(0));
    }
}
