package com.gluonhq.javaqc.ch06.repeater;

import com.gluonhq.strange.*;
import com.gluonhq.strange.gate.*;
import com.gluonhq.strange.local.*;
import com.gluonhq.strangefx.render.*;

public class Main {

    public static void main(String[] args) {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(5);
        // Step step0 = new Step();
        // step0.addGate(new X(0));
        // program.addStep(step0);
        Step step1 = new Step();
        step1.addGate(new Hadamard(1));
        step1.addGate(new Hadamard(3));
        Step step2 = new Step();
        step2.addGate(new Cnot(1,2));
        step2.addGate(new Cnot(3,4));
        Step step3 = new Step();
        step3.addGate(new Cnot(0,1));
        Step step4 = new Step();
        step4.addGate(new Hadamard(0));
        Step step5 = new Step();
        step5.addGate(new Measurement(0));
        step5.addGate(new Measurement(1));
        Step step6 = new Step();
        step6.addGate(new Cnot(1,2));
        Step step7 = new Step();
        step7.addGate(new Cz(0,2));

        Step step8 = new Step();
        step8.addGate(new Cnot(2,3));
        Step step9 = new Step();
        step9.addGate(new Hadamard(2));
        Step step10 = new Step();
        step10.addGate(new Measurement(2));
        step10.addGate(new Measurement(3));
        Step step11 = new Step();
        step11.addGate(new Cnot(3,4));
        Step step12 = new Step();
        step12.addGate(new Cz(2,4));
        program.addStep(step1);
        program.addStep(step2);
        program.addStep(step3);
        program.addStep(step4);
        program.addStep(step5);
        program.addStep(step6);
        program.addStep(step7);
        program.addStep(step8);
        program.addStep(step9);
        program.addStep(step10);
        program.addStep(step11);
        program.addStep(step12);
        program.initializeQubit(0, .4);
        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        Qubit q0 = qubits[0];
        Qubit q1 = qubits[1];
        Qubit q2 = qubits[2];
        int v0 = q0.measure();
        int v1 = q1.measure();
        int v2 = q2.measure();
        System.err.println("v = "+v2);
        Renderer.renderProgram(program);
        Renderer.showProbabilities(program, 1000);
    }  

}
