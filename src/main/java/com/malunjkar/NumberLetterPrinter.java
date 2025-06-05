package com.malunjkar;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class NumberLetterPrinter {

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition numberCondition = lock.newCondition();
    private static final Condition letterCondition = lock.newCondition();
    private static boolean isNumberTurn = true;

    public static void main(String[] args) {
        Thread numberThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                lock.lock();
                try {
                    while (!isNumberTurn) {
                        numberCondition.await();
                    }
                    System.out.print(i + " ");
                    isNumberTurn = false;
                    letterCondition.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread letterThread = new Thread(() -> {
            for (char c = 'A'; c <= 'E'; c++) {
                lock.lock();
                try {
                    while (isNumberTurn) {
                        letterCondition.await();
                    }
                    System.out.print(c + " ");
                    isNumberTurn = true;
                    numberCondition.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        });

        numberThread.start();
        letterThread.start();
    }
}
