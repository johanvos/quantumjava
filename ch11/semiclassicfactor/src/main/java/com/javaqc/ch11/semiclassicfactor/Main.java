package com.javaqc.ch11.semiclassicfactor;

import java.math.BigInteger;


public class Main {

    public static void main (String[] args) {
        int target = (int)(10000 * Math.random());
        int f = factor (target);
        System.out.println("Factored "+target+" in "+f+ " and "+target/f);
    }

    public static int factor (int N) {
        // PREPROCESSING
        // Here, the preprocessing part begins.
        System.out.println("We need to factor "+N);

        // Pick a random number `a` between 1 and `N`
        int a = 1+ (int)((N-1) * Math.random());
        System.out.println("Pick a random number a, a < N: "+a);

        // Calculate the greatest common denominator (GCD) between `a` and `N`
        int gcdan = gcd(N,a);
        System.out.println("calculate gcd(a, N):"+ gcdan); 

        // In case this GCD is not `1`, we are done, since that means the GCD is a factor of `N`
        if (gcdan != 1) return gcdan;

        // Find the periodicity of the modular exponentiatio function.
        int p = findPeriod (a, N); 
        System.out.println("period of f = "+p);
        if (p%2 == 1) { 
            // If the period turns out to be an odd number, we can't use it and have to repeat the process
            System.out.println("bummer, odd period, restart.");
            return factor(N);
        }
        // Perform some minor mathematical operations on the period to obtain a factor of `N`.
        int md = (int)(Math.pow(a, p/2) +1);
        int m2 = md%N; 
        if (m2 == 0) { 
            System.out.println("bummer, m^p/2 + 1 = 0 mod N, restart");
            return factor(N);
        }
        int f2 = (int)Math.pow(a, p/2) -1;
        return gcd(N, f2);

    }

    public static int gcd (int a, int b) {
        if (a%b == 0) return b;
        return gcd(b, a%b);
    }

    public static int findPeriod(int a, int N) {
        int r = 1;
        long mp = (long) (Math.pow(a,r)) % N;
        BigInteger bn = BigInteger.valueOf(N);
        while (mp != 1) {
            r++;
            BigInteger bi = BigInteger.valueOf(a);
            BigInteger mpd = bi.pow(r);
            BigInteger mpb = mpd.mod(bn);
            mp = mpb.longValue();
        }
        return r;
    }


}
