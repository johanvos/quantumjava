package org.redfx.javaqc.ch10.grover;

import org.redfx.strange.*;
import org.redfx.strange.algorithm.Classic;
import org.redfx.strange.gate.*;
import org.redfx.strange.local.*;
import org.redfx.strangefx.render.Renderer;

import java.util.*;
import org.redfx.strangend4j.Nd4jQuantumExecutionEnvironment;

public class Main {
    static QuantumExecutionEnvironment qee = new Nd4jQuantumExecutionEnvironment();
    static SimpleQuantumExecutionEnvironment sqee = new SimpleQuantumExecutionEnvironment();

   //  private static final int dim = 2;
    public static void main (String[] args) {
        doGrover(6,7);
//        doGrover(5, 7);
    }

    private static void doGrover(int dim, int solution) {
        int N = 1 << dim;
        double cnt = Math.PI*Math.sqrt(N)/4;
        Program p = new Program(dim);
        Step s0 = new Step();
        for (int i = 0; i < dim; i++) {
            s0.addGate(new Hadamard(i));
        }
        p.addStep(s0);
        Oracle oracle = createOracle(dim, solution);
        oracle.setCaption("O");
        Complex[][] dif = createDiffMatrix(dim);
        Oracle difOracle = new Oracle(dif);
        difOracle.setCaption("D");
        for (int i = 1; i < cnt; i++) {
            Step s1 = new Step("Oracle "+i);
            s1.addGate(oracle);
            Step s2 = new Step("Diffusion "+i);
            s2.addGate(difOracle);
            Step s3 = new Step("Prob "+i);
            s3.addGate(new ProbabilitiesGate(0));
            p.addStep(s1);
            p.addStep(s2);
            p.addStep(s3);
        }
        System.out.println(" n = "+dim+", steps = "+cnt);

        Result res = sqee.runProgram(p);
        Complex[] probability = res.getProbability();
        for (int i = 1; i < cnt; i++) {
            Complex[] ip0 = res.getIntermediateProbability(3*i);
            System.out.println("results after step "+i+": "+ip0[solution].abssqr());
//            Arrays.asList(ip0).forEach(q -> System.out.println("p: "+q.abssqr()));

        }
        System.out.println("\n");
        Renderer.renderProgram(p);
  //      Arrays.asList(probability).forEach(q -> System.out.println("p "+q.abssqr()+" R = "+q.r+" and i = "+q.i));
    }

    static Complex[][] createDiffMatrix(int dim) {
        int N = 1<<dim;
        Gate g = new Hadamard(0);
        Complex[][] matrix = g.getMatrix();
        Complex[][] h2 = matrix;
        for (int i = 1; i < dim; i++) {
            h2 = sqee.tensor(h2, matrix);
        }
        Complex[][] I2 = new Complex[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <N;j++) {
                if (i!=j) {
                    I2[i][j] = Complex.ZERO;
                } else {
                    I2[i][j] = Complex.ONE;
                }
            }
        }
        I2[0][0] = Complex.ONE.mul(-1);
        int nd = dim<<1;

        Complex[][] inter1 = mmul(h2,I2);
        Complex[][] dif = mmul(inter1, h2);
        return dif;
    }
    static <T> String arr2ToString(T[][] t) {
        StringBuffer answer = new StringBuffer();
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j <t[i].length; j++ ) {
                answer.append(t[i][j]).append(" ");
            }
            answer.append("\n");
        }
        return answer.toString();
    }

    static Complex[][] mmul(Complex[][] a, Complex[][]b) {
        int arow = a.length;
        int acol = a[0].length;
        int brow = b.length;
        int bcol = b[0].length;
        if (acol != brow) throw new RuntimeException("#cols a "+acol+" != #rows b "+brow);
        Complex[][] answer = new Complex[arow][bcol];
        for (int i = 0; i < arow; i++) {
            for (int j = 0; j < bcol; j++) {
                Complex el = Complex.ZERO;
                for (int k = 0; k < acol;k++) {
                    el = el.add(a[i][k].mul(b[k][j]));
                }
                answer[i][j] = el;
            }
        }
        return answer;
    }
    // solution must be < dim*dim
    static Oracle createOracle(int dim, int solution) {
        int N = 1<<dim;//dim<<1;
        System.err.println("dim = "+dim+" hence N = "+N);
        Complex[][] matrix = new Complex[N][N];
        for (int i = 0; i < N;i++) {
            for (int j = 0 ; j < N; j++) {
                if (i != j) {
                    matrix[i][j] = Complex.ZERO;
                } else {
                    if (i == solution) {
                        matrix[i][j] = Complex.ONE.mul(-1);
                    } else {
                        matrix[i][j] = Complex.ONE;
                    }
                }
            }
        }
        Oracle answer = new Oracle(matrix);
        return answer;
    }
}

