package com.example.init;

import com.example.Emoji;

public class B_Einstein {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("I " + Emoji.HEART + " my India.");
            }
        };
        runnable.run();


        Runnable mh=()-> System.out.println("I " + Emoji.HEART + " my Maharashtra.");
        mh.run();
    }
}
