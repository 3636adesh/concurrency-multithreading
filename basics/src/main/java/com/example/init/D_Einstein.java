package com.example.init;

import com.example.Emoji;

public class D_Einstein {

    private static final Runnable task =()->{
        System.out.println("Chalo aaj kuch tufani"+Emoji.GLASSES+" karte hai "+Emoji.WAVING_HAND);
    };

    private static final  ThreadGroup group = new ThreadGroup("Upgrad group");

    public static void main(String[] args) {
        var t=new Thread(
                ()-> System.out.println("Pal pal pal,har pal "+ Emoji.FLOWER+"...!")
        );
//        t.start();

//        c1().start();
//        c2().start();
//        c3().start();
//        c4().start();
//        c5().start();
        System.out.println(c1());
        System.out.println(c2());
        System.out.println(c3());
        System.out.println(c4());
        System.out.println(c5());

    }


    private static Thread c1(){
        return new Thread(task);
    }

    private static Thread c2(){
        return new Thread(task,"CustomThreadName");
    }

    private static Thread c3(){
        return new Thread(group, task);
    }

    private static Thread c4(){
        return new Thread(group,task,"CustomThreadName");
    }

    private static Thread c5(){
        return new Thread("CustomThreadName");
    }

}
