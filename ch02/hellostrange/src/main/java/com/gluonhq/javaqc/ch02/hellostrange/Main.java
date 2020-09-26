package com.gluonhq.javaqc.ch02.hellostrange;

import org.redfx.strange.algorithm.Classic;

public class Main {

    public static void main (String[] args) {
        System.out.println("Using high-level Strange API to generate random bits");
        System.out.println("----------------------------------------------------");
        int randomBit = Classic.randomBit();
        System.out.println("Generate one random bit, which can be 0 or 1. Result = "+randomBit);
        int cntZero = 0;
        int cntOne = 0;
        for (int i = 0; i < 10000; i++) {
            if (Classic.randomBit() > 0) {
                cntOne ++;
            } else {
                cntZero ++;
            }
        }
        System.out.println("Generated 10000 random bits, "+cntZero+" of them were 0, and "+cntOne+" were 1.");
    }
}
