package com.example.features.synchronizer;


import com.example.Emoji;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Barriers are used for blocking a group of threads until they come together at
 * a single point in order to proceed. Basically, convergence of threads.
 * <p>
 * Accepts a runnable in it's constructor to be called when the threads reach the
 * barrier, but before its unblocked
 * <p>
 * Most common implementation is cyclic barrier.
 */
public class UsingSemaphore {

    private final static String NOTE = "Datasource of books "+ Emoji.BOOK+" reading by ";

    public static void main(String[] args) {



        var semaphore = new Semaphore(5);

        Runnable task = () -> {
            try {
                if (semaphore.tryAcquire(10, TimeUnit.MINUTES)) {
                    System.out.println(NOTE +Thread.currentThread().getName());
                    Thread.sleep(2000);
                    System.out.println("Read all books by "+Thread.currentThread().getName());
                }
            }catch (Exception e){}
            finally {
                semaphore.release();
            }

        };
        var executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 4; i++) {
            executor.execute(task);
        }

        executor.shutdown();

    }
}
