package com.javaqc.ch11.classicfactor;

import java.math.BigInteger;


public class Main {

    public static void main (String[] args) {
        int target = (int)(10000 * Math.random());
        int f = factor (target);
        System.out.println("Factored "+target+" in "+f+ " and "+target/f);
    }

    public static int factor (int N) {
        int i = 1;
        int max = (int) Math.sqrt(N);
        while (i++ < max ) {
            if (N%i == 0) return i;
        }
        return N;

    }

}
