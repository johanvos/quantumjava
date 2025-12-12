package com.javaqc.sudoku;

import org.redfx.strange.Program;
import org.redfx.strange.Qubit;
import org.redfx.strange.Result;
import org.redfx.strange.Step;
import org.redfx.strange.gate.Cnot;
import org.redfx.strange.gate.Hadamard;
import org.redfx.strange.gate.RotationX;
import org.redfx.strange.gate.RotationZ;
import org.redfx.strange.gate.X;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;


public class SudokuTwoByTwo {
	// Quantum Annealing
    public static void main(String[] args) {
        int nQubits = 4;
        Program program = new Program(nQubits);

        // Parameter adjustment for better simulator visibility
        double gamma = 1.2; // Increased to enforce penalty
        double beta = 0.8;  // Increased to allow more state transitions

        // --- STEP 1: Preparation ---
        /* Board
         * Q0,Q1
         * Q2,Q3
         * 		bit =	decimal
         * Where 0 	= 	1
         * 		 1 	= 	2         
         * */
        
        Step prep = new Step();
        prep.addGate(new X(0)); // Fix Q0 = Value 2; Identity Gate for value 1
        prep.addGate(new Hadamard(1));
        prep.addGate(new Hadamard(2));
        prep.addGate(new Hadamard(3));
        program.addStep(prep);

        // --- ANNEALING LAYERS ---
        int layers = 5; // Increasing the number of layers helps with convergence

        for (int p = 0; p < layers; p++) {
            // 2. COST: Sudoku Constraints
            // Applying interaction across all constraints
            int[][] constraints = { { 0, 1 }, { 2, 3 }, { 0, 2 }, { 1, 3 } };
            for (int[] pair : constraints) {
                // Ising Interaction: CNOT -> Rz -> CNOT
                Step s1 = new Step(new Cnot(pair[0], pair[1]));
                program.addStep(s1);

                Step s2 = new Step(new RotationZ(gamma, pair[1]));
                program.addStep(s2);

                Step s3 = new Step(new Cnot(pair[0], pair[1]));
                program.addStep(s3);
            }

            // 3. MIXING (Mixer Layer)
            Step mixer = new Step();
            for (int i = 1; i < nQubits; i++) {
                mixer.addGate(new RotationX(beta, i));
            }
            program.addStep(mixer);

            // Parameter decay (Simulating the "cooling" process)
            gamma += 0.1;
            beta *= 0.9;
        }

        // Execution
        SimpleQuantumExecutionEnvironment sqee = new SimpleQuantumExecutionEnvironment();
        Result result = sqee.runProgram(program);
        Qubit[] qubits = result.getQubits();

        System.out.println("=== Sudoku 2x2: Fixed (0,0)=2 ===");
        for (int i = 0; i < qubits.length; i++) {
            double probValue2 = qubits[i].getProbability();
            // Rounding for easier reading
            System.out.printf("Cell Q%d: Value 1 (%.1f%%) | Value 2 (%.1f%%)\n", 
                i, (1.0 - probValue2) * 100, probValue2 * 100);
        }

        // Execute the program multiple times to see actual solutions collapse
        System.out.println("\n=== Solution Sampling (10 attempts) ===");
        for (int shot = 0; shot < 10; shot++) {
            Result shotResult = sqee.runProgram(program);
            Qubit[] shotQubits = shotResult.getQubits();
            
            System.out.print("Attempt " + shot + ": [");
            for (int i = 0; i < nQubits; i++) {
                // measure() returns 0 or 1, collapsing the state
                int value = shotQubits[i].measure() + 1; // +1 to convert 0,1 to 1,2
                System.out.print(value + (i < nQubits - 1 ? " " : ""));
            }
            System.out.println("]");
        }
    }
}
