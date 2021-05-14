package org.redfx.javaqc.ch09.applyoracle;

import org.redfx.strange.Complex;
import org.redfx.strange.Program;
import org.redfx.strange.QuantumExecutionEnvironment;
import org.redfx.strange.Qubit;
import org.redfx.strange.Result;
import org.redfx.strange.Step;
import org.redfx.strange.gate.Oracle;
import org.redfx.strange.gate.X;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;
import org.redfx.strangefx.render.Renderer;

public class Main {

    public static void main(String[] args) {
        System.err.println("Use 00 as input");
        try00();
        System.err.println("\nUse 01 as input");
        try01();
        System.err.println("\nUse 10 as input");
        try10();
        System.err.println("\nUse 11 as input");
        try11();
    }
    
    static void try00() {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = null;
        for (int choice = 0; choice < 4; choice++) {
            program = new Program(2);

            Step oracleStep = new Step();
            Oracle oracle = createOracle(choice);
            oracleStep.addGate(oracle);

            program.addStep(oracleStep);
            Result result = simulator.runProgram(program);
            if (choice == 1) {
                Renderer.renderProgram(program);
            }
            Qubit[] qubits = result.getQubits();

            boolean constant = (choice == 0) || (choice == 3);

            System.err.println((constant ? "C" : "B") + ", measured = "
                    + qubits[0].measure() + ", " + qubits[1].measure());
        }
    }

    static void try01() {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = null;
        for (int choice = 0; choice < 4; choice++) {
            program = new Program(2);

            Step prepareStep = new Step();
            prepareStep.addGate(new X(0));
            program.addStep(prepareStep);
            
            Step oracleStep = new Step();
            Oracle oracle = createOracle(choice);
            oracleStep.addGate(oracle);
            program.addStep(oracleStep);
            
            Result result = simulator.runProgram(program);
            Qubit[] qubits = result.getQubits();

            boolean constant = (choice == 0) || (choice == 3);

            System.err.println((constant ? "C" : "B") + ", measured = "
                    + qubits[0].measure() + ", " + qubits[1].measure());
        }
    }
    
    static void try10() {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = null;
        for (int choice = 0; choice < 4; choice++) {
            program = new Program(2);

            Step prepareStep = new Step();
            prepareStep.addGate(new X(1));
            program.addStep(prepareStep);
            
            Step oracleStep = new Step();
            Oracle oracle = createOracle(choice);
            oracleStep.addGate(oracle);
            program.addStep(oracleStep);
            
            Result result = simulator.runProgram(program);
            Qubit[] qubits = result.getQubits();

            boolean constant = (choice == 0) || (choice == 3);

            System.err.println((constant ? "C" : "B") + ", measured = "
                    + qubits[0].measure() + ", " + qubits[1].measure());
        }
    }
    
    static void try11() {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = null;
        for (int choice = 0; choice < 4; choice++) {
            program = new Program(2);

            Step prepareStep = new Step();
            prepareStep.addGate(new X(0));
            prepareStep.addGate(new X(1));
            program.addStep(prepareStep);
            
            Step oracleStep = new Step();
            Oracle oracle = createOracle(choice);
            oracleStep.addGate(oracle);
            program.addStep(oracleStep);
            
            Result result = simulator.runProgram(program);
            Qubit[] qubits = result.getQubits();

            boolean constant = (choice == 0) || (choice == 3);

            System.err.println((constant ? "C" : "B") + ", measured = "
                    + qubits[0].measure() + ", " + qubits[1].measure());
        }
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
