package com.gluonhq.javaqc.ch08.haha;

import com.gluonhq.strange.*;
import com.gluonhq.strange.gate.*;
import com.gluonhq.strange.local.*;
import com.gluonhq.strangefx.render.*;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();

        Program program = new Program(2);
        Step step0 = new Step();
        step0.addGate(new X(0));

        Step step1 = new Step();
        step1.addGate(new Hadamard(0));
        step1.addGate(new Hadamard(1));

        Step step2 = new Step();
        step2.addGate(new Hadamard(0));
        step2.addGate(new Hadamard(1));

        program.addStep(step0);
        program.addStep(step1);
        program.addStep(step2);

        Result result = simulator.runProgram(program);
        Qubit[] qubit = result.getQubits();

        Renderer.renderProgram(program);
    }

}
