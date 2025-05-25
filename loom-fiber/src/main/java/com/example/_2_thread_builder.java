package com.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class _2_thread_builder {

    public static void main(String[] args) throws InterruptedException {
        // platform thread
        var platformThread = Thread.ofPlatform()
                .name("platform-thread")
                .start(_2_thread_builder::getInfo);
        platformThread.join();

        // virtual thread
        var vitalsThread = Thread.ofVirtual()
                .name("vitals-thread")
                .start(_2_thread_builder::getInfo);
        vitalsThread.join();


    }

    private static void getInfo() {
        log.info("{} thread start", Thread.currentThread().getName());
    }
}
