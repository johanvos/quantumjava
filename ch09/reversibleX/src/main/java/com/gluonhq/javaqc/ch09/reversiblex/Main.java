package com.gluonhq.javaqc.ch09.reversiblex;

import com.gluonhq.strange.Program;
import com.gluonhq.strange.QuantumExecutionEnvironment;
import com.gluonhq.strange.Qubit;
import com.gluonhq.strange.Result;
import com.gluonhq.strange.Step;
import com.gluonhq.strange.gate.X;
import com.gluonhq.strange.local.SimpleQuantumExecutionEnvironment;
import com.gluonhq.strangefx.render.Renderer;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(1);
        Step step0 = new Step();
        step0.addGate(new X(0));

        Step step1 = new Step();
        step1.addGate(new X(0));
        program.addStep(step0);
        program.addStep(step1);
        program.initializeQubit(0,.5);

        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        Renderer.showProbabilities(program,1000);
        Renderer.renderProgram(program);
    }

}
