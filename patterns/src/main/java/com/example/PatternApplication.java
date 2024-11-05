package com.example;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * follows : <a href="https://github.com/LeonardoZ/java-concurrency-patterns">Git Repo</a>
 */



@SpringBootApplication
public class PatternApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatternApplication.class, args);
    }


    @Bean
    ApplicationRunner init(){
        return args -> System.out.println("Welcome to concurrency-patterns application");
    }
}
