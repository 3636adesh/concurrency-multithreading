package com.example.concurrency;

public record TransferRequest(
        Long fromAccountId,
        Long toAccountId,
        double amount
) {
}