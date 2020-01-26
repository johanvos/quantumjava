package com.gluonhq.javaqc.ch09.deutsch;

import com.gluonhq.strange.Complex;
import com.gluonhq.strange.Program;
import com.gluonhq.strange.QuantumExecutionEnvironment;
import com.gluonhq.strange.Qubit;
import com.gluonhq.strange.Result;
import com.gluonhq.strange.Step;
import com.gluonhq.strange.gate.Hadamard;
import com.gluonhq.strange.gate.InformalGate;
import com.gluonhq.strange.gate.Oracle;
import com.gluonhq.strange.gate.ProbabilitiesGate;
import com.gluonhq.strange.gate.X;
import com.gluonhq.strange.local.SimpleQuantumExecutionEnvironment;
import com.gluonhq.strangefx.render.Renderer;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Random random = new Random();
        Program program = null;
        for (int i = 0; i < 10; i++) {
            program = new Program(2);
            Step step0 = new Step();
            step0.addGate(new X(0));

            Step step1 = new Step();
            step1.addGate(new Hadamard(0));
            step1.addGate(new Hadamard(1));

            Step step2 = new Step();
            int choice = random.nextInt(4);
            Oracle oracle = createOracle(choice);
            step2.addGate(oracle);

            Step step3 = new Step();
            step3.addGate(new Hadamard(1));
            
            program.addStep(step0);
            program.addStep(step1);
            program.addStep(step2);
            program.addStep(step3);
            Result result = simulator.runProgram(program);
            Qubit[] qubits = result.getQubits();
            System.err.println("f = "+choice+", val = "+qubits[1].measure());
        }
        Renderer.renderProgram(program);
    }

    static Oracle createOracle(int f) {
        Complex[][] matrix = new Complex[4][4];

        switch (f) {
            case 0:
                matrix[0][0] = Complex.ONE;
                matrix[1][1] = Complex.ONE;
                matrix[2][2] = Complex.ONE;
                matrix[3][3] = Complex.ONE;
                return new Oracle(matrix);
            case 1:
                matrix[0][0] = Complex.ONE;
                matrix[1][1] = Complex.ONE;
                matrix[2][3] = Complex.ONE;
                matrix[3][2] = Complex.ONE;
                return new Oracle(matrix);
            case 2:
                matrix[0][1] = Complex.ONE;
                matrix[1][0] = Complex.ONE;
                matrix[2][2] = Complex.ONE;
                matrix[3][3] = Complex.ONE;
                return new Oracle(matrix);
            case 3:
                matrix[0][1] = Complex.ONE;
                matrix[1][0] = Complex.ONE;
                matrix[2][3] = Complex.ONE;
                matrix[3][2] = Complex.ONE;
                return new Oracle(matrix);
            default:
                throw new IllegalArgumentException("Wrong index in oracle");
        }
    }

}
