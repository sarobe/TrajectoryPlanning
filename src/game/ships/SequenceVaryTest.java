package game.ships;

import game.Action;
import math.Vector2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Purpose of this is to change a sequence of actions to one that more closely reaches a target position
public class SequenceVaryTest {

    public static void main(String[] args) {
        NonRotatingShip ship = new NonRotatingShip(new Vector2d(0,0), new Vector2d(0,0));

        Action n = new Action(0, 1);
        Action s = new Action(0, -1);
        Action w = new Action(-1,0);
        Action e = new Action(1,0);
        Action ne = new Action(1, 1);
        Action se = new Action(1, -1);
        Action nw = new Action(-1,1);
        Action sw = new Action(-1,-1);

        double numActs = 0;

        Action[] possibleActions = new Action[]{n, s, w, e, nw, ne, sw, se};
        Action[] actions = new Action[]{s, s, s, s, s, s, s, s, s};

        List<ShipState> stateHistory = new ArrayList<ShipState>();

        Vector2d destPos = new Vector2d(60, 0);

        for(Action a : actions) {
            ship.update(a);
            stateHistory.add(new ShipState(ship));
            numActs++;
        }
        System.out.println("Initial ending state: POS [" + ship.s + "], VEL [" + ship.v + "]");
        System.out.println("Desired ending state: POS [" + destPos + "]");

        double score = Double.MAX_VALUE;
        int steps = 0;
        int gap = 0;
        int maxSteps = 10000;
        Action[] candidateActionSequence = actions.clone();
        Vector2d sPredict = ship.s.copy();
        Vector2d vPredict = ship.v.copy();
        while((score > 1E-6) && (steps < maxSteps)) {
            Action newAction = possibleActions[(int)(Math.random()*possibleActions.length)];
            int swapIndex = (int)(Math.random()*actions.length);
            Action oldAction = candidateActionSequence[swapIndex];


            Vector2d calcSPredict = sPredict.copy().add((newAction.toVec().subtract(oldAction.toVec())), numActs - swapIndex);
            Vector2d calcVPredict = vPredict.copy().add((newAction.toVec().subtract(oldAction.toVec())));

            double newScore = destPos.dist(calcSPredict);
            if(newScore < score) {
                sPredict.set(calcSPredict);
                vPredict.set(calcVPredict);
                score = newScore;
                candidateActionSequence[swapIndex] = newAction;
                System.out.println("Predicting reaching \tpos " + sPredict + " and \tvel " + vPredict + " with " + newAction + " at " + swapIndex);
            }

            steps++;
//            if(steps % (maxSteps/10) == 0 && steps != 0) System.out.println(steps + " steps elapsed");
        }
        System.out.println("Best score: " + score);
        System.out.println("Resimulating");
        ship.s.zero();
        ship.v.zero();

        ship.update(candidateActionSequence);
        System.out.println("Final ending state: POS [" + ship.s + "], VEL [" + ship.v + "]");
        System.out.println("Final actions: ");
        System.out.println(Arrays.toString(candidateActionSequence));
    }
}
