package org.redfx.javaqc.ch09.oracle;

import org.redfx.strange.Complex;
import org.redfx.strange.Program;
import org.redfx.strange.QuantumExecutionEnvironment;
import org.redfx.strange.Qubit;
import org.redfx.strange.Result;
import org.redfx.strange.Step;
import org.redfx.strange.gate.Cnot;
import org.redfx.strange.gate.Hadamard;
import org.redfx.strange.gate.Oracle;
import org.redfx.strange.gate.X;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;
import org.redfx.strangefx.render.Renderer;

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
        Renderer.renderProgram(program);
        Renderer.showProbabilities(program,1000);

    }

}
