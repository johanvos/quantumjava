package com.gluonhq.javaqc.ch05.maryqubit;

import com.gluonhq.strange.Complex;
import com.gluonhq.strange.Program;
import com.gluonhq.strange.QuantumExecutionEnvironment;
import com.gluonhq.strange.Qubit;
import com.gluonhq.strange.Result;
import com.gluonhq.strange.Step;
import com.gluonhq.strange.gate.Cnot;
import com.gluonhq.strange.gate.Hadamard;
import com.gluonhq.strange.gate.Identity;
import com.gluonhq.strange.gate.X;
import com.gluonhq.strange.local.SimpleQuantumExecutionEnvironment;
import com.gluonhq.strangefx.render.Renderer;
import javafx.scene.Group;

import java.util.LinkedList;
import java.util.List;

public class StrangeBridge extends Group {

    private Program program;
    private final List<SpriteView.Lamb> qubitLamb = new LinkedList<>();
    private final Thread measureThread;
    private Result result;

    public StrangeBridge() {
        this.program = new Program(0);
        measureThread = createMeasureThread();
    }

    public void addQubit(SpriteView.Lamb lamb) {
        int nc = program.getNumberQubits() + 1;
        List<Step> oldSteps = program.getSteps();
        Program newProgram = new Program(nc);
        for (Step s : oldSteps) {
            newProgram.addStep(s);
        }
        this.program = newProgram;
        Step s1 = new Step();
        double rnd =  Math.random();
        s1.addGate(new Identity(nc-1));
        this.program.addStep(s1);
        System.err.println("Program HAS "+nc+" qubits");
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        simulator.runProgram(this.program);
        this.getChildren().clear();
        this.getChildren().add(Renderer.getRenderGroup(this.program));
        qubitLamb.add(lamb);
    }

    public void addH(int nr) {
        System.err.println("add H to "+nr);
        Step s = new Step();
        s.addGate(new Hadamard(nr));
        this.program.addStep(s);
        renderProgram();
    }

    public void addX(int nr) {
        System.err.println("add X to "+nr);
        Step s = new Step();
        s.addGate(new X(nr));
        this.program.addStep(s);
        renderProgram();
    }

    public void addCNot (int q1, int q2) {
        System.err.println("Add CNot for "+q1+", "+q2);
        Step s = new Step();
        s.addGate(new Cnot(q1, q2));
        System.err.println("created step has gates "+s.getGates());
        this.program.addStep(s);
        renderProgram();
    }

    private synchronized void renderProgram() {
        System.err.println("Strange Bridge, Render Program");
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        this.result = simulator.runProgram(this.program);
        this.getChildren().clear();
        this.getChildren().add(Renderer.getRenderGroup(this.program));
        if (measureThread.getState() == Thread.State.NEW) {
            measureThread.start();
        }
        synchronized (measureThread) {
            measureThread.notifyAll();
        }
    }

    public void clearProgram() {
        this.program.getSteps().clear();
        this.program = new Program(0);
        renderProgram();
    }

    public int getLongResult() {
        System.err.println("get long result");
        int result = 0;
        Qubit[] qubits = this.result.getQubits();
        for (Qubit q : qubits) {
            result = 2 * result;
            result = result + q.measure();
        }
        System.err.println("got long result");
        return result;
    }

    private Thread createMeasureThread() {
        System.err.println("Create measureThread");
        Thread t = new Thread() {
            @Override public void run() {
                while (true) {
                    Qubit[] qubits;
                    Complex[] probs;
                    int probCount = 0;

                    synchronized(result) {
                        result.measureSystem();
                        qubits = result.getQubits();
                        probs = result.getProbability();
                        result.printInfo();
                        for (Complex prob : probs) {
                            if (prob.abssqr() != 0) {
                                probCount++;
                            }
                        }
                    }
                    int i = 0;
                    for (Qubit q : qubits) {
                     //   System.err.println("set q["+i+"] to "+q.measure());
                        SpriteView.Lamb target = qubitLamb.get(i++);
                        target.setValue(q.measure());
                    }
                    try {
                        if (probCount == 1) {
                            // no other option, so wait for changes
                            synchronized (this) {
                                this.wait();
                            }
                        } else { // more than one option, allow to render others
                            Thread.sleep(250);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        return t;
    }

    public Program getProgram() {
        return this.program;
    }


}
