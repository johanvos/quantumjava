package com.gluonhq.javaqc.ch04.hadamard;

import com.gluonhq.strange.*;
import com.gluonhq.strange.gate.*;
import com.gluonhq.strange.local.*;

public class Main {

    public static void main(String[] args) {
        System.err.println("==================================================");
        System.err.println("Single run of a Quantum Circuit with Hadamard Gate");
        singleExecution();
        System.err.println("==================================================");
        System.err.println("\n\n");
        System.err.println("==================================================");
        System.err.println("1000 runs of a Quantum Circuit with Hadamard Gate");
        manyExecution();
        System.err.println("==================================================");
    }

    public static void singleExecution() {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(1);
        Step step = new Step();
        step.addGate(new Hadamard(0));
        program.addStep(step);
        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        Qubit zero = qubits[0];
        int value = zero.measure();
        System.out.println("Value = "+value);
    }  

    public static void manyExecution() {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(1);
        Step step = new Step();
        step.addGate(new Hadamard(0));
        program.addStep(step);
        int cntZero = 0;
        int cntOne = 0;
        for (int i = 0; i < 1000; i++) {
            Result result = simulator.runProgram(program);
            Qubit[] qubits = result.getQubits();
            Qubit zero = qubits[0];
            int value = zero.measure();
            if (value == 0) cntZero++;
            if (value == 1) cntOne++;
        }
        System.out.println("Applied Hadamard circuit 1000 times, got "
                + cntZero + " times 0 and " + cntOne + " times 1.");
    }  

}
