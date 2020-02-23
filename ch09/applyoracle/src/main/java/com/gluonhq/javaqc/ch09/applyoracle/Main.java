package com.gluonhq.javaqc.ch09.applyoracle;

import com.gluonhq.strange.Complex;
import com.gluonhq.strange.Program;
import com.gluonhq.strange.QuantumExecutionEnvironment;
import com.gluonhq.strange.Qubit;
import com.gluonhq.strange.Result;
import com.gluonhq.strange.Step;
import com.gluonhq.strange.gate.Oracle;
import com.gluonhq.strange.gate.X;
import com.gluonhq.strange.local.SimpleQuantumExecutionEnvironment;

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
