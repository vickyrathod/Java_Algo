package org.example.thread;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

public class ProducerConsumerUsingCondition {

    static ReentrantLock re = new ReentrantLock();
    static ReentrantLock rr = new ReentrantLock();
    static int producer_index = 0;
    static int consumer_index = 0;
    static int[] ar = new int[100];
    static Random r = new Random();
    static Stream<Integer> gen = Stream.generate(()->{
        return r.nextInt();
    });
    static Iterator<Integer> temp = gen.limit(300).collect(Collectors.toList()).iterator();
    static Condition write = re.newCondition();

    static Condition read = re.newCondition();

    public static void producer() {
        while(temp.hasNext()) {
            try {
                re.lock();
                while((producer_index + 1) % 100 == consumer_index) {
                    System.out.println("producer " + Thread.currentThread() + " going to sleep");
                    try {
                        write.await();
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("producer " + Thread.currentThread() + " awake");
                }
                producer_index = (producer_index + 1) % 100;
                ar[producer_index] = temp.next();
                read.signal();
                System.out.println("Producer " + Thread.currentThread() + " added " + ar[producer_index]);
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                re.unlock();
            }
            try {
                Thread.sleep(10);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static public void consumer() {
        while(temp.hasNext()) {
            try {
            re.lock();
            while(producer_index == consumer_index) {
                try {
                    System.out.println("consumer " + Thread.currentThread() + " going to sleep");
                    read.await();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Consumer consumed "+ ar[consumer_index]);
                consumer_index = (consumer_index + 1) % 100;
                write.signalAll();
            } catch(Exception e){
                e.printStackTrace();
            } finally {
                re.unlock();
            }
            try {
                Thread.sleep(40);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String args[]) throws InterruptedException {
        Thread t1 = new Thread(ProducerConsumerUsingCondition::producer);
        Thread t2 = new Thread(ProducerConsumerUsingCondition::producer);
        Thread t3 = new Thread(ProducerConsumerUsingCondition::consumer);

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}
