package com.example.concurrency;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
public class BankAccount {

    private final Long id;
    private final String owner;
    private  double balance;
    /**
     * ReentrantLock --> thread safety
     */
    private final ReentrantLock reentrantLock=new ReentrantLock();

    public BankAccount(Long id, String owner, double balance) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
    }

    public void debit(double amount) { this.balance -= amount; }
    public void credit(double amount) { this.balance += amount; }
}
