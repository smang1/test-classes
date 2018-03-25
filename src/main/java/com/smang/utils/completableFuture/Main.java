package com.smang.utils.completableFuture;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author SMA
 * @Date 08/09/2017
 */
public class Main {
    public static void main(String[] args) {
        Collection<Integer> results = new ConcurrentLinkedDeque<>();
        int tasks =10;
        CompletableFuture<?>[] allFutures = new CompletableFuture[tasks];
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int i = 0; i < tasks; i++) {
            int temp =i;
            CompletableFuture<Integer> future =CompletableFuture.supplyAsync(() -> new GenerateNumber(temp).get(),executor);
            allFutures[i] = future.thenAccept(results::add);
        }

        CompletableFuture.allOf(allFutures).thenAccept(c->{
            System.out.println(results);
        });
        executor.shutdown();
    }
}
