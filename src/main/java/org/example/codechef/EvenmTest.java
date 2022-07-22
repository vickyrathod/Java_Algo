package org.example.codechef;

import java.util.concurrent.*;

class Tester {

    int[][] arr;
    int a;

    public Tester(int[][] arr, int a){
        this.arr = arr;
        this.a = a;
    }
    public boolean compute() {
        if(a < 1)
            return true;
        for(int i = 0; i < arr.length; i ++){
            for(int j = 0; j < arr.length; j ++){
                if(i + a >= arr.length || j + a >= arr.length)
                    break;

                if((arr[i][j] + arr[i + a][j + a]) % 2 != 0 || (arr[i + a][j] + arr[i][j + a]) % 2 != 0){
                    return false;
                }
            }
        }
        return true;
    }
}

public class EvenmTest {

    public static void main(String args[]) throws ExecutionException, InterruptedException {
        int[][] ans = new EvenM(5).find();

        int n =  ans.length;
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        boolean testAns = true;
        Future<Boolean>[] threads = new Future[n];

        for(int i = 0; i < n; i ++) {
            Tester ts = new Tester(ans, i);
            threads[i] = pool.submit(() -> ts.compute());
        }

        for(int i = 0; i < n; i ++) {
            testAns = testAns && threads[i].get();
        }
        pool.shutdown();
        System.out.println(testAns);
    }
}
