package com.gluonhq.javaqc.ch05.classiccoin;

public class Main {

    private static final int count = 1000;

    private static boolean randomBit() {
        double rand = Math.random();
        return (rand > .5);
    }

    public static void main(String[] args) {
        int results[] = new int[4];
        for (int i = 0; i < count; i++) {
            boolean coinA = randomBit();
            boolean coinB = randomBit();
            if (!coinA && !coinB) results[0]++;
            if (!coinA && coinB) results[1]++;
            if (coinA && !coinB) results[2]++;
            if (coinA && coinB) results[3]++;
        }
        System.out.println("We did "+count+" experiments.");
        System.out.println("[AB]: 0 0 occured "+results[0]+" times.");
        System.out.println("[AB]: 0 1 occured "+results[1]+" times.");
        System.out.println("[AB]: 1 0 occured "+results[2]+" times.");
        System.out.println("[AB]: 1 1 occured "+results[3]+" times.");
    }  

}
