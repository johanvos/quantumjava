package com.gluonhq.javaqc.ch09.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Random;
import java.util.function.Function;

public class Main {
    static final List<Function<Integer, Integer>> functions = new ArrayList<>();
    
    static {
        Function<Integer, Integer> f1 = (Integer t) -> 0;
        Function<Integer, Integer> f2 = (Integer t) -> (t == 0) ? 0 : 1;
        Function<Integer, Integer> f3 = (Integer t) -> (t == 0) ? 1 : 0;
        Function<Integer, Integer> f4 = (Integer t) -> 1;
        functions.addAll(Arrays.asList(f1, f2, f3, f4));
    }
    
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int rnd = random.nextInt(4);
            Function<Integer, Integer> f = functions.get(rnd);
            int y0 = f.apply(0);
            int y1 = f.apply(1);
            System.err.println("f" + (rnd + 1 + " is a "
                    + ((y0 == y1) ? "constant" : "balanced")
                    + " function"));
        }
    }
    
    private Function<Integer, Integer> getFunction() {
        return null;
    }


}
