package game.ships;

import game.Action;
import game.NonRotationShip;
import math.Vector2d;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static game.Constants.*;

public class GeneralShip extends NonRotationShip {


    int[] xp = {-2, 0, 2, 0};
    int[] yp = {2, -2, 2, 0};
    int[] xpThrust = {-2, 0, 2, 0};
    int[] ypThrust = {2, 3, 2, 0};
    public double scale = 5;

    static double steerStep = 5 * Math.PI / 180;
    static double loss = 0.99;
    Color color = Color.white;
    boolean thrusting = false;

    public Action action;

    public double av; // angular velocity

    // position and velocity
    public Vector2d d;


    public GeneralShip(Vector2d s, Vector2d v, Vector2d d) {
        super(new Vector2d(s), new Vector2d(v));
        this.d = new Vector2d(d);
        // System.out.println("Made a new ship");
    }

    public double r() {
        return scale * 2.4;
    }

//    public Ship() {
//        super(new Vector2d(), new Vector2d());
//        d = new Vector2d(0, -1);
//    }
//

    public void reset() {
        s.set(width / 2, height / 2);
        v.zero();
        d.set(0, -1);
        dead = false;
        // System.out.println("Reset the ship ");
    }

    public void update() {
        update(action);
    }

    public void update(Action action) {
        if (action.thrust > 0) {
            thrusting = true;
        } else {
            thrusting = false;
        }
//        av += action.turn * steerStep;
//        d.rotate(av);
        d.rotate(action.turn * steerStep);
        v.add(d, action.thrust * t);

//        v.mul(loss);
        s.add(v);
//        System.out.println("s = " + s + " : " + thrusting + " : " + v);
//        System.out.println("d = " + d);
    }

    public String toString() {
        return s + "\t " + v + "\t" + av;
    }

    public void draw(Graphics2D g) {
        AffineTransform at = g.getTransform();
        g.translate(s.x, s.y);
        double rot = Math.atan2(d.y, d.x) + Math.PI / 2;
        g.rotate(rot);
        g.scale(scale, scale);
        g.setColor(color);
        g.fillPolygon(xp, yp, xp.length);
        if (thrusting) {
            g.setColor(Color.red);
            g.fillPolygon(xpThrust, ypThrust, xpThrust.length);
        }
        g.setTransform(at);
    }

    public void update(Action[] actions) {
        for (Action a: actions) update(a);
    }
}
