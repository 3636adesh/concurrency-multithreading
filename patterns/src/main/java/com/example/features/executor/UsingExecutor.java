package com.example.features.executor;


import com.example.Adesh;
import com.example.Emoji;
import lombok.Cleanup;

import java.util.LinkedList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;


/**
 * Thread creation can be expensive and difficult to manage manually.
 * To address this, the {@link java.util.concurrent.Executor} framework helps decouple task
 * submission from execution, providing various executor implementations to efficiently manage
 * threads.
 *
 * <p>The Java Executor framework provides six primary types of executors:</p>
 *
 * <ul>
 *   <li><strong>Single Thread Executor:</strong> Utilizes a single worker thread to process tasks sequentially.</li>
 *
 *   <li><strong>Cached Thread Pool:</strong> Provides an unbounded thread pool. Threads are reused
 *       for new tasks if available, otherwise new threads are created. This is optimal for
 *       short-lived or long-running tasks.</li>
 *
 *   <li><strong>Fixed Thread Pool:</strong> A pool with a fixed number of threads. The thread count
 *       remains constant, and any new tasks are queued if no threads are available.</li>
 *
 *   <li><strong>Scheduled Thread Pool:</strong> Allows scheduling tasks to run after a delay or periodically.
 *       This pool has a bounded number of threads.</li>
 *
 *   <li><strong>Single-Thread Scheduled Pool:</strong> A single-threaded version of the scheduled thread pool
 *       where tasks are executed sequentially with only one active task at a time.</li>
 *
 *   <li><strong>Work-Stealing Thread Pool:</strong> Based on the Fork/Join Framework, this pool applies
 *       a work-stealing algorithm to balance task execution across multiple processors,
 *       leveraging all available CPU cores for parallelism.</li>
 * </ul>
 *
 * <p>Additionally, tasks can be submitted to these executors in two different ways:</p>
 *
 * <ul>
 *   <li><strong>execute:</strong> Submits a task for execution without expecting any feedback or result (fire-and-forget).</li>
 *
 *   <li><strong>submit:</strong> Submits a task and returns a {@link java.util.concurrent.Future} object,
 *       which can be used to retrieve the result or handle the task's completion.</li>
 * </ul>
 *
 * <p>All of the above executors manage their thread pools using {@link java.util.concurrent.ThreadPoolExecutor}.
 * This class can also be used to create custom executors if required.</p>
 *
 * <p>Thread pool management methods:</p>
 *
 * <ul>
 *   <li><strong>shutdown:</strong> Initiates an orderly shutdown of the executor, waiting for currently executing
 *       tasks to finish before releasing resources.</li>
 *
 *   <li><strong>shutdownNow:</strong> Attempts to stop all actively executing tasks and returns a list of tasks
 *       that were submitted but not yet started.</li>
 * </ul>
 *
 * <p>These mechanisms simplify multithreaded programming by managing thread lifecycles,
 * optimizing resource usage, and providing robust methods for task submission and control.</p>
 *
 * @see java.util.concurrent.Executor
 * @see java.util.concurrent.Executors
 * @see java.util.concurrent.ThreadPoolExecutor
 * @see java.util.concurrent.Future
 */


@Adesh
public class UsingExecutor {

    private static void usingSingleThreadExecutor(){
        @Cleanup var singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(()-> System.out.println("Single thread executor executed"));
        singleThreadExecutor.execute(()-> System.out.println("And working fine "+Emoji.CAT));
        singleThreadExecutor.shutdown();
        try {
            singleThreadExecutor.awaitTermination(4, TimeUnit.SECONDS);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Emoji.FLOWER+"\n");
    }

    private static void usingCachedThreadPool(){
        @Cleanup var cachedThreadPool = Executors.newCachedThreadPool();
        var uuIds=new LinkedList<Future<UUID>>();
        for (int i = 0; i < 10; i++) {
            Future<UUID> future = cachedThreadPool.submit(() -> {
                var uuId = UUID.randomUUID();
                System.out.println("UUID: "+uuId+" Thread Name : "+Thread.currentThread().getName());
                return uuId;
            });
            uuIds.add(future);
        }
        cachedThreadPool.execute(() -> uuIds.forEach((f) -> {
            try {
                System.out.println("Result " + f.get() + " from thread " + Thread.currentThread().getName());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }));
        cachedThreadPool.shutdown();
        try {
            cachedThreadPool.awaitTermination(4,TimeUnit.SECONDS);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Emoji.FLOWER+"\n");

    }

    public static void usingFixedThreadPool() {
        System.out.println("=== FixedThreadPool ===");
        var fixedPool = Executors.newFixedThreadPool(4);
        var uuids = new LinkedList<Future<UUID>>();
        for (int i = 0; i < 20; i++) {
            var submitted = fixedPool.submit(() -> {
                var randomUUID = UUID.randomUUID();
                System.out.println("UUID " + randomUUID + " from " + Thread.currentThread().getName());
                return randomUUID;
            });
            uuids.add(submitted);
        }
        fixedPool.execute(() -> uuids.forEach((f) -> {
            try {
                System.out.println("Result " + f.get() + " from " + Thread.currentThread().getName());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }));
        fixedPool.shutdown();
        try {
            fixedPool.awaitTermination(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n");
    }

    public static void usingScheduledThreadPool() {
        System.out.println("=== ScheduledThreadPool ===");
        var scheduledThreadPool = Executors.newScheduledThreadPool(4);
        scheduledThreadPool.scheduleAtFixedRate(() -> System.out.println("1) Print every 2s"), 0, 2, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleAtFixedRate(() -> System.out.println("2) Print every 2s"), 0, 2, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleWithFixedDelay(() -> System.out.println("3) Print every 2s delay"), 0, 2,
                TimeUnit.SECONDS);

        try {
            scheduledThreadPool.awaitTermination(6, TimeUnit.SECONDS);
            scheduledThreadPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n");
    }


    public static void usingSingleTreadScheduledExecutor() {
        System.out.println("=== SingleThreadScheduledThreadPool ===");
        var singleThreadScheduler = Executors.newSingleThreadScheduledExecutor();
        singleThreadScheduler.scheduleAtFixedRate(() -> System.out.println("1) Print every 2s"), 0, 2, TimeUnit.SECONDS);
        singleThreadScheduler.scheduleWithFixedDelay(() -> System.out.println("2) Print every 2s delay"), 0, 2,
                TimeUnit.SECONDS);

        try {
            singleThreadScheduler.awaitTermination(6, TimeUnit.SECONDS);
            singleThreadScheduler.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n");

    }

    public static void usingWorkStealingThreadPool() {
        System.out.println("=== WorkStealingThreadPool ===");
        var workStealingPool = Executors.newWorkStealingPool();

        workStealingPool.execute(() -> System.out.println("Prints normally"));

        Callable<UUID> generatesUUID = UUID::randomUUID;
        var severalUUIDsTasks = new LinkedList<Callable<UUID>>();
        for (int i = 0; i < 20; i++) {
            severalUUIDsTasks.add(generatesUUID);
        }

        try {
            var futureUUIDs = workStealingPool.invokeAll(severalUUIDsTasks);
            for (var future : futureUUIDs) {
                if (future.isDone()) {
                    var uuid = future.get();
                    System.out.println("New UUID :" + uuid);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        try {
            workStealingPool.awaitTermination(6, TimeUnit.SECONDS);
            workStealingPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n");
    }

    public static void main(String[] args) {
        usingSingleThreadExecutor();
        usingCachedThreadPool();
        usingFixedThreadPool();
        usingScheduledThreadPool();
        usingSingleTreadScheduledExecutor();
        usingWorkStealingThreadPool();

    }
}
