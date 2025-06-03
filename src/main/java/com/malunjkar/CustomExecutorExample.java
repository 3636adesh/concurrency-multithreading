package com.malunjkar;

import lombok.Cleanup;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

public class CustomExecutorExample {

    public static void main(String[] args) {
        @Cleanup var customForkJoinPool = new ForkJoinPool(4);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return "Processed in custom pool: " + Thread.currentThread().getName();
                }, customForkJoinPool
        );
        completableFuture.thenAccept(result -> System.out.println("Result: " + result));
        completableFuture.join();
    }
}
