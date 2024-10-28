package com.example.features.atomic;


import com.example.Adesh;
import com.example.Emoji;
import lombok.Getter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class demonstrates the use of atomic classes from the {@link java.util.concurrent.atomic} package.
 *
 * <p>Atomic operations are compound actions that either completely succeed or completely fail, ensuring that
 * there are no inconsistent or intermediate states during execution. They are widely used in concurrent
 * programming to handle shared variables without using locks.</p>
 *
 * <p>The classes in the {@code java.util.concurrent.atomic} package provide atomic operations on single variables.
 * These operations include get and set (read and write), behaving similarly to volatile variables but with
 * additional atomic guarantees.</p>
 *
 * <p>A key method, {@code compareAndSet}, is commonly used in non-blocking algorithms. This method attempts to
 * set a new value to a specified field if and only if the current value matches the expected one, returning
 * a boolean indicating the success of the operation. All of this is done atomically, ensuring minimal blocking.</p>
 *
 * <p>Some of the important classes in this package include:</p>
 * <ul>
 *   <li>{@link java.util.concurrent.atomic.AtomicBoolean}</li>
 *   <li>{@link java.util.concurrent.atomic.AtomicLong}</li>
 *   <li>{@link java.util.concurrent.atomic.AtomicReference}</li>
 *   <li>{@link java.util.concurrent.atomic.AtomicMarkableReference}</li>
 *   <li>{@link java.util.concurrent.atomic.AtomicReferenceFieldUpdater}</li>
 * </ul>
 *
 * <p>These classes help implement high-performance, lock-free, and thread-safe algorithms,
 * minimizing synchronization overhead in concurrent applications.</p>
 *
 * @see java.util.concurrent.atomic.AtomicInteger
 * @see java.util.concurrent.atomic.AtomicBoolean
 * @see java.util.concurrent.atomic.AtomicLong
 * @see java.util.concurrent.atomic.AtomicReference
 */

@Adesh
public class UsingAtomics {

    @Getter
    static class AtomicCounter{
        AtomicInteger atomicInteger = new AtomicInteger(0);

        public void increment(){
            atomicInteger.incrementAndGet();
        }

        public void decrement(){
            atomicInteger.decrementAndGet();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Emoji.MUSIC);
        AtomicCounter atomicCounter = new AtomicCounter();
        var cachePool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10_000_000; i++) {
            cachePool.execute(atomicCounter::increment);
        }

        cachePool.shutdown();
        cachePool.awaitTermination(4000, TimeUnit.SECONDS);
        System.out.println("Result shound be 10000000: Actual result is: " + atomicCounter.getAtomicInteger());



    }
}
