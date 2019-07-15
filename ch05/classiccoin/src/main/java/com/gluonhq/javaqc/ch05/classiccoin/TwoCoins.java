package com.gluonhq.javaqc.ch05.classiccoin;

public class TwoCoins {

    private static boolean randomBit() {
        double rand = Math.random();
        return (rand > .5);
    }

    public static int[] calculate(int count) {
        int results[] = new int[4];
        for (int i = 0; i < count; i++) {
            boolean coinA = randomBit();
            boolean coinB = randomBit();
            if (!coinA && !coinB) results[0]++;
            if (!coinA && coinB) results[1]++;
            if (coinA && !coinB) results[2]++;
            if (coinA && coinB) results[3]++;
        }
        return results;
    }
}
