package com.example;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

@Slf4j
public class _3_how_many_platform_thread {

    public static void main(String[] args) throws InterruptedException {
        var barrier = new CyclicBarrier(100_000);
        var threads = IntStream.range(0, 100_000)
                .mapToObj(i -> new Thread(() -> {
                    try {
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        throw new AssertionError(e);
                    }
                }))
                .toList();
        var i = 0;
        for (var thread : threads) {
            System.out.println(i++);
            thread.start();
        }
        for (var thread : threads) {
            thread.join();
        }
    }
}