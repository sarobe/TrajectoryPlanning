package game.ships;

import game.Action;
import math.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    NonRotatingShip ship;
    Vector2d destPos;

    Action n = new Action(0, 1);
    Action s = new Action(0, -1);
    Action w = new Action(-1,0);
    Action e = new Action(1,0);
    Action ne = new Action(1, 1);
    Action se = new Action(1, -1);
    Action nw = new Action(-1,1);
    Action sw = new Action(-1,-1);
    Action nop = new Action(0, 0);
    Action[] possibleActions = new Action[]{n, s, w, e, nw, ne, sw, se};
    int currIndex;
    List<Action> actions = new ArrayList<Action>();

    public Controller (NonRotatingShip ship, Vector2d destPoint) {
        this.ship = ship;
        this.destPos = destPoint.copy();
        think();
    }

    private void think() {
        // start with a random list of actions
        for(int i = 0; i < ControllerTest.timesteps; i++) {
            actions.add(possibleActions[(int) (Math.random() * possibleActions.length)]);
        }

        // simulate!
        int numActs = 0;
        ShipState originState = new ShipState(ship);
        for(Action a : actions) {
            ship.update(a);
            numActs++;
        }
        // copy this state
        ShipState endState = new ShipState(ship);
        // restore original ship state
        originState.loadStateInto(ship);

        // improve!
        double score = Double.MAX_VALUE;
        int steps = 0;
        int maxSteps = 10000;
        List<Action> candidateActionSequence = new ArrayList<Action>();
        candidateActionSequence.addAll(actions);
        Vector2d sPredict = endState.s;
        Vector2d vPredict = endState.v;
        while((score > 1E-6) && (steps < maxSteps)) {
            Action newAction = possibleActions[(int)(Math.random()*possibleActions.length)];
            int swapIndex = (int)(Math.random()*actions.size());
            Action oldAction = candidateActionSequence.get(swapIndex);
            Vector2d calcSPredict = sPredict.copy().add((newAction.toVec().subtract(oldAction.toVec())), numActs - swapIndex);
            Vector2d calcVPredict = vPredict.copy().add((newAction.toVec().subtract(oldAction.toVec())));
            double newScore = destPos.dist(calcSPredict);
            if(newScore < score) {
                sPredict.set(calcSPredict);
                vPredict.set(calcVPredict);
                score = newScore;
                candidateActionSequence.set(swapIndex, newAction);
            }
            steps++;
        }

        // use!
        actions = candidateActionSequence;
    }

    public Action getAction() {
        Action a;
        if(currIndex >= actions.size()) {
            a = nop;
            think();
            System.out.println("Ship pos: " + ship.s + " Dest pos: " + destPos);
        } else {
            a = actions.get(currIndex);
        }
        currIndex++;
        return a;
    }
}
