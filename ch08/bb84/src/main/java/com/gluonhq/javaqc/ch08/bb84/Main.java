package com.gluonhq.javaqc.ch08.bb84;

import com.gluonhq.strange.*;
import com.gluonhq.strange.gate.*;
import com.gluonhq.strange.local.*;
import com.gluonhq.strangefx.render.*;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        // Alice and Bob will exchange SIZE qubits, hence the resulting key will be maximum SIZE bits.
        final int SIZE = 8;
        Random random = new Random();

        boolean[] aliceBits = new boolean[SIZE]; // random bits chosen by Alice
        boolean[] bobBits = new boolean[SIZE]; // bits measured by Bob
        boolean[] aliceBase = new boolean[SIZE]; // random bases chosen by Alice
        boolean[] bobBase = new boolean[SIZE]; // random bases chosen by Bob

        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(SIZE);
        Step prepareStep = new Step();
        Step superPositionStep = new Step();
        Step superPositionStep2 = new Step();
        Step measureStep = new Step();

        for (int i = 0; i < SIZE; i++) {
            aliceBits[i] = random.nextBoolean();
            // if Alice's bit is 1, apply a X gate to the |0> state
            if (aliceBits[i]) prepareStep.addGate(new X(i));
            aliceBase[i] = random.nextBoolean();
            // if Alice's base for this bit is 1, apply a H gate
            if (aliceBase[i]) superPositionStep.addGate(new Hadamard(i));
            bobBase[i] = random.nextBoolean();
            // if Bob decides to measure in base 1, apply a H gate
            if (bobBase[i]) superPositionStep2.addGate(new Hadamard(i));
            // Finally, Bob measures the result
            measureStep.addGate(new Measurement(i));
        }

        program.addStep(prepareStep);
        program.addStep(superPositionStep);
        program.addStep(superPositionStep2);
        program.addStep(measureStep);

        Result result = simulator.runProgram(program);
        Qubit[] qubit = result.getQubits();

        int[] measurement = new int[SIZE];
        StringBuffer key = new StringBuffer();
        for (int i = 0; i < SIZE; i++) {
            measurement[i] = qubit[i].measure();
            bobBits[i] = measurement[i] == 1;
            if (aliceBase[i] != bobBase[i]) {
                // If the random bases chosen by Alice and Bob for this bit are different, ignore values
                System.err.println("Different bases used, ignore values "+aliceBits[i]+" and "+ bobBits[i]);
            } else {
                // Alice and Bob picked the same bases. The inital value from Alice matches the measurement from Bob.
                // this bit now becomes part of the secret key
                System.err.println("Same bases used. Alice sent " + (aliceBits[i] ? "1" : "0") + " and Bob received " + (bobBits[i] ? "1" : "0"));
                key.append(aliceBits[i] ? "1" : "0");
            }
        }
        System.err.println("Secret key = "+key);

        Renderer.renderProgram(program);
    }

}
