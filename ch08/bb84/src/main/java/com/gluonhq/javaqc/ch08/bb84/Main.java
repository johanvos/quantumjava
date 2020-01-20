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
            if (aliceBits[i]) prepareStep.addGate(new X(i));
            aliceBase[i] = random.nextBoolean();
            if (aliceBase[i]) superPositionStep.addGate(new Hadamard(i));
            bobBase[i] = random.nextBoolean();
            if (bobBase[i]) superPositionStep2.addGate(new Hadamard(i));
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
                System.err.println("Different bases used, ignore values "+aliceBits[i]+" and "+ bobBits[i]);
            } else {
                System.err.println("Same bases used. Alice sent " + (aliceBits[i] ? "1" : "0") + " and Bob received " + (bobBits[i] ? "1" : "0"));
                key.append(aliceBits[i] ? "1" : "0");
            }
        }
        System.err.println("Secret key = "+key);

        Renderer.renderProgram(program);
    }

}
