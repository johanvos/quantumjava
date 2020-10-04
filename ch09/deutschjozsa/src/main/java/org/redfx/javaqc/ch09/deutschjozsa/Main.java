package org.redfx.javaqc.ch09.deutschjozsa;

import org.redfx.strange.Complex;
import org.redfx.strange.Program;
import org.redfx.strange.QuantumExecutionEnvironment;
import org.redfx.strange.Qubit;
import org.redfx.strange.Result;
import org.redfx.strange.Step;
import org.redfx.strange.gate.Hadamard;
import org.redfx.strange.gate.Oracle;
import org.redfx.strange.gate.X;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;
import org.redfx.strangefx.render.Renderer;

import java.util.Random;

public class Main {

    static final int N = 3;
    
    public static void main(String[] args) {

        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Random random = new Random();
        Program program = null;
        for (int i = 0; i < 10; i++) {
            program = new Program(N+1);
            Step step0 = new Step();
            step0.addGate(new X(N));

            Step step1 = new Step();
            for (int j = 0; j < N+1; j++) {
                step1.addGate(new Hadamard(j));
            }

            Step step2 = new Step();
            int choice = random.nextInt(2);
            choice = 1;
            Oracle oracle = createOracle(choice);
            step2.addGate(oracle);

            Step step3 = new Step();
            for (int j = 0; j < N; j++) {
                step3.addGate(new Hadamard(j));
            }
            
            program.addStep(step0);
            program.addStep(step1);
            program.addStep(step2);
            program.addStep(step3);
            Result result = simulator.runProgram(program);
            Qubit[] qubits = result.getQubits();
            System.err.println("f = "+choice+", val = "+qubits[0].measure());
        }
        Renderer.renderProgram(program);
    }

    static Oracle createOracle(int f) {
        int dim = 2 << N;
        int half = dim/2;
        
        Complex[][] matrix = new Complex[dim][dim];

        switch (f) {
            case 0:
                for (int i = 0; i < dim; i ++) {
                    matrix[i][i] = Complex.ONE;
                }
                return new Oracle(matrix);
            case 1:
                for (int i = 0; i < dim; i ++) {
                    if (i%2 == 0) {
                        matrix[i][i] = Complex.ONE;
                    } else {
                        if (i < half) {
                            matrix[i][i + half] = Complex.ONE;
                        } else{
                            matrix[i][i - half] = Complex.ONE;
                        }
                    }
                }
                return new Oracle(matrix);
         
            default:
                throw new IllegalArgumentException("Wrong index in oracle");
        }
    }

}
