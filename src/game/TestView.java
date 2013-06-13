package game;

import utilities.JEasyFrame;

import static game.Constants.*;
public class TestView {
    public static void main(String[] args) {
        rand.setSeed(19);
        ProblemInstance pi = new ProblemInstance(10);
        View view = new View(pi);
        new JEasyFrame(view, "Continuous PTSP");
        Evaluator eval = new Evaluator(pi, view);
        double[] a = new double[]{
                1, 1, 1, -2,
                0, 0, 0, -1,
                0.1, -0.5, 1, 1,
                1, 0, 1, 1,
                1, 1, 1, 0,
        };
        eval.eval(a);
        System.out.println(view.wpList);
    }
}
