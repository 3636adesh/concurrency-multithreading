package com.example.init;

import com.example.Emoji;

public class A_Einstein {

    static class MyRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("Maduri Dixit "+ Emoji.FLOWER);
        }
    }

    public static void main(String[] args) {
        var runnable=new MyRunnable();
        runnable.run();
    }

}
