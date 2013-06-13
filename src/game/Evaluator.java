package game;

import static game.Constants.*;

public class Evaluator {

    ProblemInstance pi;
    public View view;
    public boolean animate = true;

    // keep track of best solution
    public double[] best;
    public double bestVal;

    public Evaluator(ProblemInstance pi) {
        this.pi = pi;
    }

    public Evaluator(ProblemInstance pi, View view) {
        this.pi = pi;
        this.view = view;
        bestVal = -10000;
    }

    public double eval(double[] x) {
        pi.reset();
        Ship ship = pi.newShip();
        int i;
        if (view != null) view.reset();
        for (i = 0; i < x.length; i += 2) {
            Action action = new Action(x[i], x[i + 1], pi.stepsPerAction);
            for (int j = 0; j < action.t; j++) {
                ship.update(action);
                // System.out.println("Ship.v : " + ship.v);
                pi.update(ship.s);
                if (view != null) {
                    view.wpList.add(ship.s.copy());
                    if (animate) {
                        view.repaint();
                        sleep();
                    }
                }
                if (pi.gameOver()) break;
            }
            if (pi.gameOver()) break;
        }
        // score is waypoint score plus time remaining
//        System.out.println("WayPoint score = " + pi.wpScore());
//        System.out.println("Time left = " + pi.nTicks);
        double tot = pi.totalScore();
        // something funny with CMA not keeping best solution
        if (tot >= bestVal) {
            bestVal = tot;
            best = new double[x.length];
            for (int k = 0; k < x.length; k++) best[k] = x[k];
            System.out.println("New best of: " + bestVal);
            System.out.println("nTicks: " + pi.nTicks);
            System.out.println(pi.wpl);
        }
        // System.out.println("Returning: " + tot);
        return tot;
    }

    public void sleep() {
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
        }
    }
}
