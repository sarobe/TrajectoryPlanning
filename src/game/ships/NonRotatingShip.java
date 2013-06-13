package game.ships;

import game.Action;
import game.NonRotationShip;
import math.Vector2d;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static game.Constants.*;

public class NonRotatingShip extends NonRotationShip {

    int[] xp = {-2, 0, 2, 0};
    int[] yp = {2, -2, 2, 0};
    int[] xpThrust = {-2, 0, 2, 0};
    int[] ypThrust = {2, 3, 2, 0};
    public double scale = 5;

    static double steerStep = 5 * Math.PI / 180;
    static double loss = 0.5;
    Color color = Color.white;
    boolean thrusting = false;

    public Action action;
    Vector2d xa = new Vector2d(1,0);
    Vector2d ya = new Vector2d(0,1);


    public NonRotatingShip(Vector2d s, Vector2d v) {
        super(new Vector2d(s), new Vector2d(v));
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
        dead = false;
        // System.out.println("Reset the ship ");
    }

    public void update() {
        update(action);
    }

    public NonRotatingShip update(Action action) {
//        if (action.thrust > 0) {
//            thrusting = true;
//        } else {
//            thrusting = false;
//        }
        v.add(xa, action.thrust * t);
        v.add(ya, action.turn * t);
//        v.mul(loss);
        s.add(v);
//        System.out.println("s = " + s + " : " + thrusting + " : " + v);
//        System.out.println("d = " + d);
        return this;
    }

    public String toString() {
        return s + "\t " + v;
    }

    public void draw(Graphics2D g) {
        AffineTransform at = g.getTransform();
        g.translate(s.x, s.y);
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
