package com.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class _1_starting_thread {

    public static void main(String[] args) throws InterruptedException {
        //platform thread
        var platformThread = new Thread(() -> log.info("platform {}", Thread.currentThread()));
        platformThread.start();
        platformThread.join();

        //virtual thread
        var virtualThread = Thread.startVirtualThread(() -> log.info("virtual {}", Thread.currentThread()));
        virtualThread.join();
    }
}
