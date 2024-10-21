package com.example.features.future;

import java.util.Random;
import java.util.concurrent.*;


/**
 * Demonstrates the usage of {@link FutureTask} in Java for managing and executing
 * asynchronous tasks that return a result.
 *
 * <p>{@code FutureTask<V>} represents an asynchronous computation. It allows
 * developers to start a task, query its status, and retrieve its result once
 * the computation is complete. {@code FutureTask} is commonly used for
 * long-running tasks or I/O operations.
 *
 * <p>The {@code FutureTask} has three main states:
 * <ul>
 *   <li>Waiting to run: The task has been created but not yet started.
 *   <li>Running: The task is currently being executed.
 *   <li>Completed: The task has finished, either by returning a result or through cancellation.
 * </ul>
 *
 * <p>The task can be canceled using the {@code cancel()} method if it is no longer needed.
 *
 * <p>Once the task is complete, the result can be retrieved using the {@code get()} method.
 * If the task has not finished, {@code get()} will block the calling thread until the
 * result is available, unless a timeout is specified.
 *
 * <p>In this example, a random sleep is introduced to simulate a long-running task. The
 * main thread waits for the result of the task for up to 2 seconds before a
 * {@link TimeoutException} is thrown.
 *
 * <p>This example demonstrates:
 * <ul>
 *   <li>How to create and execute a {@code FutureTask}.
 *   <li>How to retrieve the result of the task with a timeout using {@code get()}.
 *   <li>How to handle exceptions that may occur during task execution.
 * </ul>
 *
 * @see java.util.concurrent.FutureTask
 * @see java.util.concurrent.ExecutorService
 * @see java.util.concurrent.Callable
 */
public class UsingFutureTasks {

    public static void main(String[] args) {
        Callable<Integer> callable = () -> {
            int random = new Random().nextInt(10) * 100;
            System.out.println("Preparing to execute");
            Thread.sleep(random);
            System.out.println("Executed - " + random);
            return random;
        };

        FutureTask<Integer> futureTask = new FutureTask<>(callable);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(futureTask);

        try {
            Integer value = futureTask.get(2, TimeUnit.SECONDS);
            System.out.println("Value is " + value);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
