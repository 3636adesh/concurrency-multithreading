package com.malunjkar;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

/**
 * The common ForkJoinPool is shared by:
 *
 * 1.parallelStream()
 * 2.CompletableFuture.supplyAsync() and friends (by default)
 */
public class ForkJoinPoolExample {

    public static void main(String[] args) {
        var listOfNumbers = IntStream.range(1, 10)
                .boxed()
                .toList();
      try(var customThreadPool = new ForkJoinPool(4)){
           customThreadPool.submit(
                  () -> {
                      listOfNumbers.parallelStream().forEach(
                              number -> {
                                  System.out.println("Thread: " + Thread.currentThread().getName() + " Value: " + number);
                                  try {
                                      Thread.sleep(100); // Simulate some work
                                  } catch (InterruptedException e) {
                                      Thread.currentThread().interrupt();
                                  }
                              }
                      );
                  }
          );
      }catch (Exception e) {
          e.printStackTrace();
      }
    }
}
