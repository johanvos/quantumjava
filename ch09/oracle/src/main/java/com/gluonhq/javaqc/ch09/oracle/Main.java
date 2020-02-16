package com.gluonhq.javaqc.ch09.oracle;

import com.gluonhq.strange.Complex;
import com.gluonhq.strange.Program;
import com.gluonhq.strange.QuantumExecutionEnvironment;
import com.gluonhq.strange.Qubit;
import com.gluonhq.strange.Result;
import com.gluonhq.strange.Step;
import com.gluonhq.strange.gate.Cnot;
import com.gluonhq.strange.gate.Hadamard;
import com.gluonhq.strange.gate.Oracle;
import com.gluonhq.strange.gate.X;
import com.gluonhq.strange.local.SimpleQuantumExecutionEnvironment;
import com.gluonhq.strangefx.render.Renderer;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Random random = new Random();
        Program program = new Program(2);
        Step step1 = new Step();
        step1.addGate(new Hadamard(1));


        Complex[][] matrix =  new Complex[][]{
                {Complex.ONE,Complex.ZERO,Complex.ZERO,Complex.ZERO},
                {Complex.ZERO,Complex.ONE,Complex.ZERO,Complex.ZERO},
                {Complex.ZERO,Complex.ZERO,Complex.ZERO,Complex.ONE},
                {Complex.ZERO,Complex.ZERO,Complex.ONE,Complex.ZERO}
        };

        Oracle oracle = new Oracle(matrix);

        Step step2 = new Step();
        step2.addGate(oracle);

        program.addStep(step1);
        program.addStep(step2);

        Result result = simulator.runProgram(program);
        Renderer.showProbabilities(program,1000);
        Renderer.renderProgram(program);

    }

}
