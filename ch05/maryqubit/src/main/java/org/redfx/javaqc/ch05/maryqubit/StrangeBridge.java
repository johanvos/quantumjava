/*
 * BSD 3-Clause License
 *
 * Copyright (c) 2019, Johan Vos and Stephen Chin
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the name of the copyright holder nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.redfx.javaqc.ch05.maryqubit;

import org.redfx.strange.Complex;
import org.redfx.strange.Program;
import org.redfx.strange.QuantumExecutionEnvironment;
import org.redfx.strange.Qubit;
import org.redfx.strange.Result;
import org.redfx.strange.Step;
import org.redfx.strange.gate.Cnot;
import org.redfx.strange.gate.Hadamard;
import org.redfx.strange.gate.Identity;
import org.redfx.strange.gate.X;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;
import org.redfx.strange.ui.QubitBoard;
import org.redfx.strangefx.render.Renderer;
import javafx.scene.Group;

import java.util.LinkedList;
import java.util.List;

public class StrangeBridge extends Group {

    private Program program;
    private final List<SpriteView.Lamb> qubitLamb = new LinkedList<>();
    private final Thread measureThread;
    private Result result;
    private QubitBoard board;

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
//        System.err.println("Program HAS "+nc+" qubits");
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        simulator.runProgram(this.program);
        this.getChildren().clear();
      //  this.getChildren().add(Renderer.getRenderGroup(this.program));
        qubitLamb.add(lamb);
        renderProgram();
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
//        System.err.println("Strange Bridge, Render Program");
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        this.result = simulator.runProgram(this.program);
        this.getChildren().clear();
        if (board != null) {
            Renderer.disable(board);
        }
        board = Renderer.getRenderGroup(this.program);
        this.getChildren().add(board);
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
        this.qubitLamb.clear();
        renderProgram();
    }

    public int getLongResult() {
//        System.err.println("get long result");
        int result = 0;
        Qubit[] qubits = this.result.getQubits();
        for (Qubit q : qubits) {
            result = 2 * result;
            result = result + q.measure();
        }
//        System.err.println("got long result");
        return result;
    }

    private Thread createMeasureThread() {
      //  System.err.println("Create measureThread");
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
//                        System.err.println("set q["+i+"] to "+q.measure());
                        SpriteView.Lamb target = qubitLamb.get(i++);
//                        System.err.println("target = "+target);
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
