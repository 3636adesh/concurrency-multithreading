package com.example.explore;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ExploreAtomic {

    private AtomicBoolean atomicBoolean = new AtomicBoolean(true);
    private AtomicInteger atomicInteger = new AtomicInteger(1);
    private AtomicReference<String> atomicReference = new AtomicReference<>("");

}
