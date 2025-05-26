package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 💡 BankApplication - Entry Point of the Concurrent Money Transfer System
 *
 * <p>This is the main class for the Spring Boot application that simulates
 * a multi-threaded bank transfer system. The application exposes a RESTful API
 * where users can transfer money between accounts concurrently.
 *
 * <p>Key Features:
 * <ul>
 *     <li>Thread-safe transfer operations</li>
 *     <li>Deadlock prevention mechanisms</li>
 *     <li>Accurate and consistent account balances</li>
 *     <li>Transfers are handled by a custom thread pool executor</li>
 * </ul>
 *
 * <p>To start the application, run the main method. Spring Boot will initialize
 * the application context and expose the necessary REST endpoints.
 */
@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}
}

