package com.example.concurrency;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class AccountService {

    private final ConcurrentHashMap<Long, BankAccount> accounts = new ConcurrentHashMap<>();

    public AccountService() {
        accounts.put(1L, new BankAccount(1L, "Alice", 1000));
        accounts.put(2L, new BankAccount(2L, "Bob", 1000));
    }

    @Async("customExecutor")
    public void transfer(TransferRequest transferRequest) {
        var from = accounts.get(transferRequest.fromAccountId());
        var to = accounts.get(transferRequest.toAccountId());
        double amount = transferRequest.amount();

        try {
            if (lock(from)) {
                try {
                    if (lock(to)) {
                        try {
                            if(from.getBalance()>=amount){
                                 from.debit(amount);
                                 to.credit(amount);
                                System.out.printf("[%s] Transferred %.2f from %s to %s\n", Thread.currentThread().getName(), amount, from.getOwner(), to.getOwner());
                            }
                        } finally {
                            unlock(to);
                        }
                    }
                } finally {
                    unlock(from);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static boolean lock(BankAccount bankAccount) throws InterruptedException {
        return bankAccount.getReentrantLock().tryLock(1, TimeUnit.SECONDS);
    }

    private static void unlock(BankAccount bankAccount) {
        bankAccount.getReentrantLock().unlock();

    }
}
