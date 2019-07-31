package com.gluonhq.javaqc.ch05.bellstate;

import com.gluonhq.strange.*;
import com.gluonhq.strange.gate.*;
import com.gluonhq.strange.local.*;
import com.gluonhq.strangefx.render.*;

public class Main {

    public static void main(String[] args) {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(2);
        Step step1 = new Step();
        step1.addGate(new Hadamard(0));
        program.addStep(step1);
        Step step2 = new Step();
        step2.addGate(new Cnot(0,1));
        // step2.addGate(new Hadamard(1));
        program.addStep(step2);
        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        Qubit q0 = qubits[0];
        Qubit q1 = qubits[1];
        int v0 = q0.measure();
        int v1 = q1.measure();
        Renderer.renderProgram(program);
        Renderer.showProbabilities(program, 1000);
    }  

}
