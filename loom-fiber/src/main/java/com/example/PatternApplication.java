package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * follows : <a href="https://github.com/forax/loom-fiber">Git Repo</a>
 */


@Slf4j
@SpringBootApplication
public class PatternApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatternApplication.class, args);
    }


    @Bean
    ApplicationRunner init() {
        return args -> log.info("Welcome to loop-fiber application");
    }
}
