package org.redfx.javaqc.ch08.superposition;

import org.redfx.strange.*;
import org.redfx.strange.gate.*;
import org.redfx.strange.local.*;
import org.redfx.strangefx.render.*;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        final int SIZE = 4;
        Random random = new Random();

        boolean[] aliceBits = new boolean[SIZE];
        for (int i = 0 ; i < SIZE; i++) {
            aliceBits[i] = random.nextBoolean();
        }

        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(SIZE);
        Step prepareStep = new Step();
        Step superPositionStep = new Step();
        Step superPositionStep2 = new Step();
        Step measureStep = new Step();
        for (int i = 0; i < SIZE; i++) {
            if (aliceBits[i]) prepareStep.addGate(new X(i));
            superPositionStep.addGate(new Hadamard(i));
            superPositionStep2.addGate(new Hadamard(i));
            measureStep.addGate(new Measurement(i));
        }

        program.addStep(prepareStep);
        program.addStep(superPositionStep);
        program.addStep(superPositionStep2);
        program.addStep(measureStep);

        Result result = simulator.runProgram(program);
        Qubit[] qubit = result.getQubits();

        int[] measurement = new int[SIZE];
        boolean[] bobBits = new boolean[SIZE];
        for (int i = 0; i < SIZE; i++) {
            measurement[i] = qubit[i].measure();
            bobBits[i] = measurement[i] == 1;
            System.err.println("Alice sent "+(aliceBits[i] ? "1" : "0") + " and Bob received "+(bobBits[i] ? "1" : "0"));
        }

        Renderer.renderProgram(program);
    }

}
