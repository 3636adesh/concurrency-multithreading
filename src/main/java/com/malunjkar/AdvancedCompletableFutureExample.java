package com.malunjkar;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class AdvancedCompletableFutureExample {

    static ExecutorService customExecutor = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        List<Integer> userIds = Arrays.asList(1, 2, 3, 4, 5);

        List<CompletableFuture<UserOrderInfo>> futures = userIds.stream()
                .map(userId ->
                        getUser(userId)
                                .thenCompose(user -> getOrders(userId)
                                        .thenApply(orders -> new UserOrderInfo(user, orders)))
                                .exceptionally(ex -> {
                                    System.err.println("Failed to fetch data for user " + userId + ": " + ex.getMessage());
                                    return new UserOrderInfo(null, Collections.emptyList()); // fallback
                                })
                ).toList();

        // Combine all futures
        CompletableFuture<Void> all = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        // Wait for all to finish and collect results
        CompletableFuture<List<UserOrderInfo>> resultListFuture = all.thenApply(v ->
                futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
        );

        List<UserOrderInfo> results = resultListFuture.join(); // Final result
        results.forEach(System.out::println);

        customExecutor.shutdown();
    }

    // Simulated async user fetch
    static CompletableFuture<User> getUser(int userId) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(300); // simulate delay
            if (userId == 3) throw new RuntimeException("User not found");
            return new User(userId, "User" + userId);
        }, customExecutor);
    }

    // Simulated async order fetch
    static CompletableFuture<List<Order>> getOrders(int userId) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(200); // simulate delay
            return Arrays.asList(
                    new Order("ORD-" + userId + "-1"),
                    new Order("ORD-" + userId + "-2")
            );
        }, customExecutor);
    }

    static void sleep(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    // Models
    static class User {
        int id;
        String name;
        User(int id, String name) { this.id = id; this.name = name; }
        public String toString() { return "User{id=" + id + ", name='" + name + "'}"; }
    }

    static class Order {
        String orderId;
        Order(String orderId) { this.orderId = orderId; }
        public String toString() { return orderId; }
    }

    static class UserOrderInfo {
        User user;
        List<Order> orders;
        UserOrderInfo(User user, List<Order> orders) {
            this.user = user;
            this.orders = orders;
        }
        public String toString() {
            return "UserOrderInfo{user=" + user + ", orders=" + orders + "}";
        }
    }
}
