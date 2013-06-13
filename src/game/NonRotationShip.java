package game;

import math.Vector2d;

import java.awt.*;

public abstract class NonRotationShip {
    public Vector2d s,v;
    public boolean isTarget;
    public boolean dead;
    public double r;

    protected NonRotationShip(Vector2d s, Vector2d v) {
        this.s = new Vector2d(s);
        this.v = new Vector2d(v);
    }

    public abstract void update();
    public abstract void draw(Graphics2D g);

    public void hit() {
        dead  = true;
    }

    public double r() {
        return r;
    }
}
