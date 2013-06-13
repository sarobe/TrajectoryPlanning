package game.ships;

import game.Action;
import math.Vector2d;

/**
 *  Purpose of this is to experiment with the effects of changing an early action
 *  on the later position and velocity
 */
public class IncChangeTest {
    public static void main(String[] args) {
        NonRotatingShip ship1 = new NonRotatingShip(new Vector2d(0,0), new Vector2d(0,0));
        NonRotatingShip ship2 = new NonRotatingShip(new Vector2d(0,0), new Vector2d(0,0));
//        GeneralShip ship1 = new GeneralShip(new Vector2d(0,0), new Vector2d(0,0), new Vector2d(1,0));
//        GeneralShip ship2 = new GeneralShip(new Vector2d(0,0), new Vector2d(0,0), new Vector2d(1,0));

        Action n = new Action(0, 1);
        Action s = new Action(0, -1);
        Action w = new Action(-1,0);
        Action e = new Action(1,0);

//        Action fwd = new Action(1, 1);
//        Action rotCW = new Action(0, 1);
//        Action rotCCW = new Action(0, -1);

        Action nop = new Action(0,0);

        double numActs = 0;
        int repeats = 1;

        Action differ = n;
        Action[] actions = new Action[]{n, s, n, n, e, e, w, w, e, s, n, n};
        Action[] altActions = new Action[]{n, s, n, n, e, e, w, w, e, s, n, n};

//        ship1.update(nop);
//        ship2.update(differ);
//
//        numActs += 1;

        for(int i=0; i<repeats; i++) {
            ship1.update(actions);
            ship2.update(altActions);
            numActs += actions.length;
            System.out.println("Ship 1: " + ship1);
            System.out.println("Ship 2: " + ship2);
        }
        System.out.println();

//        Vector2d sDiff = ship2.s.copy().subtract(ship1.s);
//        Vector2d vDiff = ship2.v.copy().subtract(ship1.v);
//        System.out.println("Diff: " + sDiff + "\t" + vDiff);
        System.out.println("No. Actions: " + numActs);
//        System.out.println("Inc Change Per Action: " + sDiff.mul(1/numActs) + "\t" + vDiff.mul(1/numActs));
//
        System.out.println();

        // theoretical postulations:
        // FOR ALL OF THESE, THE DEFAULT PARAMETERS ARE ASSUMED AS BASIS
        // when only linear velocity is affected, the ending position is equal to change in velocity * number of remaining actions
        // and the ending velocity is the same as it was, simply added to by the change
        // FOR SIMPLE LINEAR VELOCITY:
        Vector2d sPredict = ship1.s.copy().add(differ.toVec(), numActs);
        Vector2d vPredict = ship1.v.copy().add(differ.toVec());

        // when the rotation of the ship is affected, the velocity will be rotated by the changed action's impact
        // the ending position will be rotated by the angle of change with the point of change used as the centre of rotation
        // FOR SIMPLE ROTATION WITH NO ANGULAR INERTIA:

//        Vector2d sPredict = ship1.s.copy();
//        // rotate by the angle
//        sPredict.rotate(differ.turn * GeneralShip.steerStep);
//        // assuming a starting rotation of 0, any forward motion is simple
//        sPredict.add(new Vector2d(differ.thrust * numActs, 0));
//
//        Vector2d vPredict = ship1.v.copy();
//        // rotate by the angle
//        vPredict.rotate(differ.turn * GeneralShip.steerStep);
//        // assuming a starting rotation of 0, any forward motion is simple
//        vPredict.add(new Vector2d(differ.thrust, 0));

        // with rotational inertia, the effect is more complicated
        // forward impulses still have an easily predictable effect, but the impact of rotational intertia is harder to predict

//        Vector2d sPredict = ship1.s.copy();
//        sPredict.rotate((differ.turn * GeneralShip.steerStep) * (numActs));
//        // linear change is simple to predict
//        sPredict.add(new Vector2d(differ.thrust * numActs, 0));
//
//
//        Vector2d vPredict = ship1.v.copy();
//        // rotational change has a knock on effect due to the angular inertia
//        double angularDiff = (differ.turn * GeneralShip.steerStep) * (numActs-1);
//        vPredict.rotate(angularDiff);
//        // linear change is simple to predict
//        vPredict.add(new Vector2d(differ.thrust, 0));
//
//        // final angular velocity is not hard to predict
//        double avPredict = ship1.av;
//        avPredict += differ.turn * GeneralShip.steerStep;


        // calculating predictions for LINEAR loss factor WITH NO ROTATION
//        Vector2d sPredict = ship1.s.copy();
//        sPredict.add(differ.toVec(), Math.pow(NonRotatingShip.loss, numActs) + 1);
//
//        Vector2d vPredict = ship1.v.copy();
//        vPredict.add(differ.toVec(), Math.pow(NonRotatingShip.loss, numActs));

//        double endAngle = ship2.v.theta();
//        double predictedEndAngle = ship1.v.theta() + angularDiff;
//
//        System.out.println(endAngle);
//        System.out.println(predictedEndAngle);
//        System.out.println();

        Vector2d sError = ship2.s.copy().subtract(sPredict);
        Vector2d vError = ship2.v.copy().subtract(vPredict);
//        double avError = ship2.av - avPredict;

        System.out.println("Predicted final position: " + sPredict);
        System.out.println("Error: " + sError);
        System.out.println();
        System.out.println("Predicted final velocity: " + vPredict);
        System.out.println("Error: " + vError);
//        System.out.println();
//        System.out.println("Predicted final ang. velocity: " + avPredict);
//        System.out.println("Error: " + avError);
    }

}
