package game;

import math.Vector2d;

import java.awt.*;

public class WayPoint extends NonRotationShip {

    static int rad = 70;
    static int step = 7;
    int score;
    int maxScore = rad / step;

    static Color[] rings = {
            new Color(128, 128, 0),
            new Color(0, 128, 0),
            new Color(0, 0, 128),
            new Color(128, 0, 128),
            new Color(128, 255, 128),
            new Color(128, 255, 0),
            new Color(0, 255, 128),
            new Color(255, 0, 128),
            new Color(0, 128, 255),
            new Color(255, 128, 100),
    };

    public WayPoint(Vector2d s) {
        super(s, new Vector2d());
        score = 0;
    }

    public WayPoint(double x, double y) {
        super(new Vector2d(x,y), new Vector2d());
        score = 0;
    }

    public void reset() { score = 0; }

    @Override
    public void update() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Action a) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public int updatePoint(Vector2d p) {
        int dist = (int) (s.dist(p));
        score = Math.max((rad+step-1-dist)/step, score);
        return score;
    }

    @Override
    public void draw(Graphics2D g) {
        int red = 0; int redStep = 250 / (rad / step);
        int x = (int) s.x;
        int y = (int) s.y;
        int c = 0;
        for (int r=rad; r>=1; r -= step) {
            g.setColor(new Color(red, red/2, 150));
            // g.setColor(rings[c]);
            g.fillOval(x-r, y-r, 2*r, 2*r);
            red += redStep;
            c++;
        }
    }

    public String toString() {
        return String.format("( %s, %d )", s.toString(), score);
    }
}
