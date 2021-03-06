package org.redfx.javaqc.ch07.add1;

import org.redfx.strange.Program;
import org.redfx.strange.QuantumExecutionEnvironment;
import org.redfx.strange.Qubit;
import org.redfx.strange.Result;
import org.redfx.strange.Step;
import org.redfx.strange.gate.Cnot;
import org.redfx.strange.gate.X;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;
import org.redfx.strangefx.render.Renderer;

public class Main {

    public static void main (String[] args) {
        
        Program program = new Program(2);
        Step step0 = new Step(new Cnot(0,1));

        program.addSteps(step0);
        
        QuantumExecutionEnvironment qee = new SimpleQuantumExecutionEnvironment();
        Result result = qee.runProgram(program);
        Qubit[] qubits = result.getQubits();
        for (int i = 0; i < 2; i++) {
            System.err.println("Qubit["+i+"]: "+qubits[i].measure());
        }
        Renderer.renderProgram(program);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int sum = add(i,j);
                System.err.println("Adding "+i+" + " + j+" = "+sum);
            }
        }
    }
    
    static int add(int a, int b) {
        Program program = new Program(2);
        Step prep = new Step();
        if (a > 0) prep.addGate(new X(0));
        if (b > 0) prep.addGate(new X(1));
        Step step0 = new Step(new Cnot(0,1));
        program.addSteps(prep, step0);
        QuantumExecutionEnvironment qee = new SimpleQuantumExecutionEnvironment();
        Result result = qee.runProgram(program);
        Qubit[] qubits = result.getQubits();
        return qubits[1].measure();
    }

}

