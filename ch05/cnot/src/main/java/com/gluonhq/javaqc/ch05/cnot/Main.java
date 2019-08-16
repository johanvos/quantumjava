package com.gluonhq.javaqc.ch05.cnot;

import com.gluonhq.strange.*;
import com.gluonhq.strange.gate.*;
import com.gluonhq.strange.local.*;
import com.gluonhq.strangefx.render.*;

public class Main {

    public static void main(String[] args) {
        run00();
        run01();
        run10();
        run11();
    }

    private static void run00() {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(2);
        Step step1 = new Step();
        step1.addGate(new Cnot(0,1));
        program.addStep(step1);
        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        Qubit q0 = qubits[0];
        Qubit q1 = qubits[1];
        int v0 = q0.measure();
        int v1 = q1.measure();
        System.out.println("v0 = "+v0+" and v1 = "+v1);
        Renderer.renderProgram(program);
    }  

    private static void run01() {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(2);
        Step step1 = new Step();
        step1.addGate(new X(1));
        program.addStep(step1);
        Step step2 = new Step();
        step2.addGate(new Cnot(0,1));
        program.addStep(step2);
        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        Qubit q0 = qubits[0];
        Qubit q1 = qubits[1];
        int v0 = q0.measure();
        int v1 = q1.measure();
        System.out.println("v0 = "+v0+" and v1 = "+v1);
        Renderer.renderProgram(program);
    }  

    private static void run10() {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(2);
        Step step1 = new Step();
        step1.addGate(new X(0));
        program.addStep(step1);
        Step step2 = new Step();
        step2.addGate(new Cnot(0,1));
        program.addStep(step2);
        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        Qubit q0 = qubits[0];
        Qubit q1 = qubits[1];
        int v0 = q0.measure();
        int v1 = q1.measure();
        System.out.println("v0 = "+v0+" and v1 = "+v1);
        Renderer.renderProgram(program);
    }  

    private static void run11() {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(2);
        Step step1 = new Step();
        step1.addGate(new X(0));
        step1.addGate(new X(1));
        program.addStep(step1);
        Step step2 = new Step();
        step2.addGate(new Cnot(0,1));
        program.addStep(step2);
        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        Qubit q0 = qubits[0];
        Qubit q1 = qubits[1];
        int v0 = q0.measure();
        int v1 = q1.measure();
        System.out.println("v0 = "+v0+" and v1 = "+v1);
        Renderer.renderProgram(program);
    }  

}
