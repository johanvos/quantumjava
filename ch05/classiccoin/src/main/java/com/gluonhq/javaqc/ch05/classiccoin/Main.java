package com.gluonhq.javaqc.ch05.classiccoin;

public class Main {

    private static final int count = 1000;

    private static boolean randomBit() {
        double rand = Math.random();
        return (rand > .5);
    }

    public static void main(String[] args) {
        int results[] = TwoCoins.calculate(count);
        System.out.println("We did "+count+" experiments.");
        System.out.println("0 0 occured "+results[0]+" times.");
        System.out.println("0 1 occured "+results[1]+" times.");
        System.out.println("1 0 occured "+results[2]+" times.");
        System.out.println("1 1 occured "+results[3]+" times.");
    }


}
