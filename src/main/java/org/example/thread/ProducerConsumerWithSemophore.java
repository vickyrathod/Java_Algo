package org.example.thread;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProducerConsumerWithSemophore {
    static Semaphore se = new Semaphore(100);
    static BlockingQueue<Integer> bq = new ArrayBlockingQueue(100);

    static Random r = new Random();
    static Stream<Integer> gen = Stream.generate(()->{
        return r.nextInt();
    });
    static Iterator<Integer> temp = gen.limit(300).collect(Collectors.toList()).iterator();

    public static void consumer(){
        while(temp.hasNext()){
            try {
                se.release();
                Integer i = bq.poll();
                System.out.println("Consumer got: " + i);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void producer(){
        while(temp.hasNext()){
            try {
                int x = temp.next();
                bq.add(new Integer(x));
                se.acquire();
                System.out.println("Producer got :" + x);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Thread t1 = new Thread(ProducerConsumerWithSemophore::consumer);
        Thread t2 = new Thread(ProducerConsumerWithSemophore::producer);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
