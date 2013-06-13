package game;

import math.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static game.Constants.*;

public class ProblemInstance {
    public List<WayPoint> wpl;
    public static int size = 400;
    int stepsPerAction = 20;
    int border = 100;
    int score;
    int maxScore;
    int maxTime = 1000;
    int nBullsEyes;
    int nTicks; // number of calls to update
    // game is over after maxTime ticks
    Ship ship;

    public ProblemInstance(int n) {
        wpl = new ArrayList<WayPoint>();
        for (int i=0; i<n; i++) {
            Vector2d s = new Vector2d(rand.nextInt(size), rand.nextInt(size));
            wpl.add(new WayPoint(s));
        }
        setMaxScore();
    }

    public ProblemInstance(List<WayPoint> wpl) {
        this.wpl = wpl;
        setMaxScore();
    }

    public boolean gameOver() {
        return nBullsEyes == wpl.size() || nTicks >= maxTime;
    }

    private void setMaxScore() {
        maxScore = 0;
        for (WayPoint wp : wpl) maxScore += wp.maxScore;
        System.out.println("Max Score = " + maxScore);
    }

    public void reset() {
        for (WayPoint wp : wpl) wp.reset();
        nTicks = 0;
        score = 0;
        nBullsEyes = 0;
    }

    public Ship newShip() {
        // creates a new ship in the starting location
        ship = new Ship(new Vector2d(size/2, size),
                new Vector2d(), new Vector2d(0, -1));
        return ship;
    }

    public Ship getShip() {
        if (ship == null) newShip();
        return ship;
    }

    public int update(Vector2d p) {
        // go over the way points adding up the scores
        score = 0;
        nBullsEyes = 0;
        for (WayPoint wp : wpl) {
            score += wp.updatePoint(p);
            if (wp.score == wp.maxScore) {
                nBullsEyes++;
                // System.out.println("A bulls eye!");
            }
        }
        nTicks++;
        if (nBullsEyes == wpl.size()) {
            // System.out.println("Max bulls eyes!!!!! : " + nBullsEyes);
        }
//        if (nBullsEyes > 0)
//            System.out.println("nBulls = " + nBullsEyes);
        return score;
    }

    public int totalScore() {
        // int bonus = score == maxScore ? (maxTime - nTicks) : 0;
        int bonus = score == maxScore ? (maxTime - nTicks) : 0;
        // System.out.println("Bonus = " + bonus);
        return 100 * score - nTicks;
    }
}
