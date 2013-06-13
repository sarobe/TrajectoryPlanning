package game;

import math.Vector2d;

public class Action {
    public static int tDefault = 20;
    public double thrust;
    public double turn;
    public double t;

    public Action(double thrust, double turn) {
        this(thrust, turn, tDefault);
    }

    public Action(double thrust, double turn, double t) {
        this.thrust = thrust;
        this.turn = turn;
        this.t = t;
    }

    public Vector2d toVec(){
        return new Vector2d(thrust, turn);
    }

    public String toString() {
        return thrust + " : " + turn + " : " + t;
//        return thrust + " : " + turn;
    }
}
