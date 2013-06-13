package game;

import utilities.ElapsedTimer;
import utilities.JEasyFrame;
import utilities.StatSummary;

import static game.Constants.rand;

public class TestEvaluator {
    public static void main(String[] args) {
        rand.setSeed(19);
        ProblemInstance pi = new ProblemInstance(10);
        Evaluator eval = new Evaluator(pi);
        double[] a = new double[]{
                2, 1, 1, -1,
                2, 0, 1, -1,
                10, -0.5, 1, 1,
                1, 0, 1, 1,
                0, 1, 1, 0,
        };
        ElapsedTimer t = new ElapsedTimer();
        StatSummary ss = new StatSummary();
        for (int i = 1; i <= 10000; i++) {
            // System.out.println(i + "\t " + eval.eval(a));
            ss.add(eval.eval(a));
        }
        System.out.println(ss);
        System.out.println(t);
        System.out.println(pi.totalScore() + " : " + pi.totalScore() + " : " + pi.nTicks);
    }
}
