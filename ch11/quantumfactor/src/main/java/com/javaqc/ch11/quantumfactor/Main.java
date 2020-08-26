package com.javaqc.ch11.quantumfactor;

import com.gluonhq.strange.algorithm.Classic;

public class Main {

    public static void main (String[] args) {
        int target = 15;
        int f = Classic.qfactor (target);
        System.out.println("QFactored "+target+" in "+f+ " and "+target/f);
    }

}
