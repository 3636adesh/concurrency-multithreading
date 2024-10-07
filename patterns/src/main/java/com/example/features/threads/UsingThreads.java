package com.example.features.threads;

import com.example.Adesh;
import com.example.Emoji;

/**
 * Java supports OS threads.
 * </br>
 * Thread run parallel in CPU cores
 */

@Adesh
public class UsingThreads {

    public static void main(String[] args) throws InterruptedException {

        //Creating
        var created = new Thread();
        created.start();

        //Assign
        var threadWithTask = new Thread(
                () -> System.out.println("Inside thread with task" + Thread.currentThread().getName())
        );
        threadWithTask.start();

        //Interrupting a thread
        Runnable interruptedTask = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Im not interrupted " + Thread.currentThread().getName());
            }
        };
        Thread interrupted = new Thread(interruptedTask);
        interrupted.start();
        Thread.sleep(5);
        interrupted.interrupt();

        System.out.println("Java "+Emoji.COFFEE);
    }
}
