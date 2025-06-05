package com.malunjkar;


/**
 * Suppose you have 2 threads.
 * <p>
 * One of them prints (1,2,3...) and the other one prints (A,B,C,...).
 * <p>
 * How will you ensure that they run in a sequence so that it prints(1,A,2,B...)?
 */
public class Questions_1 {

    private static final Object lock = new Object();
    private static boolean numberTurn = true;  // Start with number

    public static void main(String[] args) {
        Thread numberThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                synchronized (lock) {
                    while (!numberTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.print(i + " ");
                    numberTurn = false;
                    lock.notify();
                }
            }
        });


        Thread letterThread = new Thread(() -> {
            for (char c = 'A'; c <= 'E'; c++) {
                synchronized (lock) {
                    while (numberTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.print(c + " ");
                    numberTurn = true;
                    lock.notify();
                }
            }
        });

        numberThread.start();
        letterThread.start();
    }
}
