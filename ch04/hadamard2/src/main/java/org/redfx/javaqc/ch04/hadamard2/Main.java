package org.redfx.javaqc.ch04.hadamard2;

import org.redfx.strange.*;
import org.redfx.strange.gate.*;
import org.redfx.strange.local.*;

public class Main {

    public static void main(String[] args) {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(1);
        Step step = new Step();
        step.addGate(new Hadamard(0));
        program.addStep(step);
        Step step2 = new Step();
        step2.addGate(new Hadamard(0));
        program.addStep(step2);
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
        System.out.println("Applied H-H circuit and evaluated 1000 times, got "+cntZero+" times 0 and "+cntOne+" times 1.");
    }  

}
