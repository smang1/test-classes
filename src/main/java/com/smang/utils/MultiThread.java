package com.smang.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author SMA
 * @Date 01/09/2017
 */
public class MultiThread {

    public static void main(String[] args) {
        // create a pool of threads, 10 max jobs will execute in parallel
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        // submit jobs to be executing by the pool
        for(int i = 0; i < 5; i++){
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.format("Printing from thread "));
                }
            });
        }

        // once you've submitted your last job to the service it should be shut down
        threadPool.shutdown();
        // wait for the threads to finish if necessary
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
