package game.ships;

import game.Action;
import game.NonRotationShip;
import math.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    NonRotationShip ship;
    Vector2d destPos;
    Vector2d destVel;
    Vector2d sPredict;
    Vector2d vPredict;
    int numActs = 0;
    double bestScore = Double.MAX_VALUE;

    Action n = new Action(0, 1);
    Action s = new Action(0, -1);
    Action w = new Action(-1,0);
    Action e = new Action(1,0);
    Action ne = new Action(1, 1);
    Action se = new Action(1, -1);
    Action nw = new Action(-1,1);
    Action sw = new Action(-1,-1);
    Action[] possibleActions = new Action[]{n, s, w, e, nw, ne, sw, se};

//    Action fwd = new Action(1, 0);
//    Action rotCW = new Action(0, 1);
//    Action rotCCW = new Action(0, -1);
    Action nop = new Action(0, 0);
//    Action[] possibleActions = new Action[]{fwd, rotCW, rotCCW, nop};
    int currIndex;
    List<Action> actions = new ArrayList<Action>();

    public Controller (NonRotationShip ship, Vector2d destPoint, Vector2d destVel) {
        this.ship = ship;
        this.destPos = destPoint.copy();
        this.destVel = destVel.copy();
        sPredict = new Vector2d();
        vPredict = new Vector2d();
        setup();
    }

    public void think() {
        if(currIndex <= actions.size()-1 ) {
            Action newAction = possibleActions[(int)(Math.random()*possibleActions.length)];
            int swapIndex = (int)(currIndex + (Math.random()*(actions.size() - currIndex)));
            Action oldAction = actions.get(swapIndex);

            // for non rotating ships
            Vector2d calcSPredict = sPredict.copy().add((newAction.toVec().subtract(oldAction.toVec())), numActs - swapIndex);
            Vector2d calcVPredict = vPredict.copy().add((newAction.toVec().subtract(oldAction.toVec())));

            // for moving OR rotating ships
//            Vector2d calcSPredict = sPredict.copy();
//            calcSPredict.rotate((newAction.turn - oldAction.turn) * GeneralShip.steerStep);
//            calcSPredict.add(new Vector2d((newAction.thrust - oldAction.thrust) * numActs, 0));
//
//            Vector2d calcVPredict = vPredict.copy();
//            calcVPredict.rotate((newAction.turn - oldAction.turn) * GeneralShip.steerStep);
//            calcVPredict.add(new Vector2d(newAction.thrust - oldAction.thrust, 0));

            double newScore = destPos.dist(calcSPredict) + (destVel.dist(calcVPredict)*10);
            if(newScore < bestScore) {
                sPredict.set(calcSPredict);
                vPredict.set(calcVPredict);
                bestScore = newScore;
                actions.set(swapIndex, newAction);
                System.out.println("Score: " + bestScore);
            }
        }
    }

    public void initThink(int numsteps) {
        for(int i = 0; i < numsteps; i++) {
            think();
        }
    }

    private void setup() {
        // start with a random list of actions
        for(int i = 0; i < ControllerTest.timesteps; i++) {
            actions.add(possibleActions[(int) (Math.random() * possibleActions.length)]);
        }

        // simulate!
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
        sPredict = endState.s;
        vPredict = endState.v;
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
