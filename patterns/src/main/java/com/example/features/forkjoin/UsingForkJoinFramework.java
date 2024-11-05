package com.example.features.forkjoin;

import com.example.Adesh;

import java.util.Properties;

@Adesh
public class UsingForkJoinFramework {


    public static void main(String[] args) {
        Properties properties = System.getProperties();
        for(String key : properties.stringPropertyNames()) {
            System.out.println(key + ": " + properties.getProperty(key));
        }
    }
}
