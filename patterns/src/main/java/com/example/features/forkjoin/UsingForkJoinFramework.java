package com.example.features.forkjoin;

import com.example.Adesh;

@Adesh
public class UsingForkJoinFramework {


        public static void main(String[] args) {
            int cores = Runtime.getRuntime().availableProcessors();
            System.out.println("Number of platform threads supported: " + cores);
        }


}
