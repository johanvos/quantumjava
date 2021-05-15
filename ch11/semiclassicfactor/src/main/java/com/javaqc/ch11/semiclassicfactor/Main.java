package com.javaqc.ch11.semiclassicfactor;

import java.math.BigInteger;


public class Main {

    static final int MAX_TRIES = 100;

    public static void main (String[] args) {
        int target = (int)(10000 * Math.random());
        int cnt = 0;
        int f = -1;
        while (cnt < MAX_TRIES) {
            f = factor(target);
            if (f > 0) break;
            cnt++;
        }
        if (cnt < MAX_TRIES) {
            System.out.println("Factored "+target+" in "+f+ " and "+target/f);
        } else {
            System.out.println("Failed to factor " + target+" after " + MAX_TRIES+" tries. Might be a prime number?");
        }
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
            return -1;
        }
        // Perform some minor mathematical operations on the period to obtain a factor of `N`.
        int md = 1;
        for (int i = 0; i < p/2; i ++) {
            md = (md * a) % N;
        }
        md = md + 1;
        int m2 = md % N;
        if (m2 == 0) { 
            System.out.println("bummer, m^p/2 + 1 = 0 mod N, restart");
            return -1;
        }
        int factor = gcd(N, md);
        return factor;
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
