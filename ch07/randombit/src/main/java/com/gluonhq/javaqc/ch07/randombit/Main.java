package com.gluonhq.javaqc.ch07.randombit;

import com.gluonhq.strange.Program;
import com.gluonhq.strange.QuantumExecutionEnvironment;
import com.gluonhq.strange.Qubit;
import com.gluonhq.strange.Result;
import com.gluonhq.strange.Step;
import com.gluonhq.strange.gate.Cnot;
import com.gluonhq.strange.gate.Hadamard;
import com.gluonhq.strange.gate.ProbabilitiesGate;
import com.gluonhq.strange.gate.X;
import com.gluonhq.strange.local.SimpleQuantumExecutionEnvironment;
import com.gluonhq.strangefx.render.Renderer;

public class Main {

    private static final int dim = 4;

    public static void main (String[] args) {
        
        Program program = new Program(dim);
        Step p0 = new Step (new ProbabilitiesGate(0));
        Step step0 = new Step(new Hadamard(0), new X(3));
        Step p1 = new Step (new ProbabilitiesGate(0));
        Step step1 = new Step(new Cnot(0,1));
        Step p2 = new Step (new ProbabilitiesGate(0));

        program.addSteps(p0, step0, p1, step1, p2);
        
        QuantumExecutionEnvironment qee = new SimpleQuantumExecutionEnvironment();
        Result result = qee.runProgram(program);
        Qubit[] qubits = result.getQubits();
        for (int i = 0; i < dim; i++) {
            System.err.println("Qubit["+i+"]: "+qubits[i].measure());
        }
        Renderer.renderProgram(program);
    }

}

