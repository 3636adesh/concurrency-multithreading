package com.example.init;

import com.example.Emoji;

public class C_Einstein {

    static class MyThread extends Thread{

        @Override
        public void run(){
            System.out.println("All good "+ Emoji.THUMBS_UP+".");
        }

    }

    public static void main(String[] args) {

        var t1=new MyThread();
        t1.start();
    }
}
