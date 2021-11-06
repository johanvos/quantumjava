package org.redfx.javaqc.ch09.deutsch;

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
        Program program = null;
        for (int i = 0; i < 10; i++) {
            program = new Program(2);
            Step step0 = new Step();
            step0.addGate(new X(1));

            Step step1 = new Step();
            step1.addGate(new Hadamard(0));
            step1.addGate(new Hadamard(1));

            Step step2 = new Step();
            int choice = random.nextInt(4);

            Oracle oracle = createOracle(choice);
            step2.addGate(oracle);

            Step step3 = new Step();
            step3.addGate(new Hadamard(0));
            
            program.addStep(step0);
            program.addStep(step1);
            program.addStep(step2);
            program.addStep(step3);
            Result result = simulator.runProgram(program);
            Qubit[] qubits = result.getQubits();
            System.err.println("f = "+(choice+1)+", val = "+qubits[0].measure());
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
                matrix[1][3] = Complex.ONE;
                matrix[2][2] = Complex.ONE;
                matrix[3][1] = Complex.ONE;
                return new Oracle(matrix);
            case 2:
                matrix[0][2] = Complex.ONE;
                matrix[1][1] = Complex.ONE;
                matrix[2][0] = Complex.ONE;
                matrix[3][3] = Complex.ONE;
                return new Oracle(matrix);
            case 3:
                matrix[0][2] = Complex.ONE;
                matrix[1][3] = Complex.ONE;
                matrix[2][0] = Complex.ONE;
                matrix[3][1] = Complex.ONE;
                return new Oracle(matrix);
            default:
                throw new IllegalArgumentException("Wrong index in oracle");
        }
    }

}
