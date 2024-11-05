package com.example.spring.concurrent;


import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableAsync
@EnableScheduling
@Service
public class AsyncExecutor{

    @Async("testTask")
    @Scheduled(fixedRate = 5000)
    void test(){

        System.out.println("Task started by: " + Thread.currentThread().getName());
        // Simulate a task by sleeping for 2 seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Task completed by: " + Thread.currentThread().getName());
    }
}
