package com.gluonhq.javaqc.ch08.naive;

import com.gluonhq.strange.*;
import com.gluonhq.strange.gate.*;
import com.gluonhq.strange.local.*;
import com.gluonhq.strangefx.render.*;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        final int SIZE = 4;
        Random random = new Random();

        boolean[] bits = new boolean[SIZE];
        for (int i = 0 ; i < SIZE; i++) {
            bits[i] = random.nextBoolean();
        }

        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(SIZE);
        Step step1 = new Step();
        Step step2 = new Step();
        for (int i = 0; i < SIZE; i++) {
            if (bits[i]) step1.addGate(new X(i));
            step2.addGate(new Measurement(i));
        }

        program.addStep(step1);
        program.addStep(step2);

        Result result = simulator.runProgram(program);
        Qubit[] qubit = result.getQubits();

        int[] measurement = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            measurement[i] = qubit[i].measure();
            System.err.println("Alice sent "+(bits[i] ? "1" : "0") + " and Bob received "+measurement[i]);
        }

        Renderer.renderProgram(program);
    }

}
