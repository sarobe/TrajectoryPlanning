package game.ships;

import game.Action;
import game.NonRotationShip;
import math.Vector2d;
import utilities.JEasyFrame;

import java.util.ArrayList;
import java.util.List;

public class ControllerTest {

    public static boolean showPathConstruction = true;
    public static int timesteps = 50;
    public static int setupsteps = 2000;

    public static void main(String[] args) throws InterruptedException {
        NonRotationShip ship = new NonRotatingShip(new Vector2d(250,10), new Vector2d(0,0));
        Vector2d destPos = new Vector2d(250, 450);
        Vector2d destVel = new Vector2d(0, 0);

        Controller c = new Controller(ship, destPos, destVel);
        ControllerView v = new ControllerView(ship, c, destPos);

        new JEasyFrame(v, "Controller Test");

        int counter = 0;
        if(showPathConstruction) {
            while(counter < setupsteps) {
                synchronized(ControllerTest.class) {
                    c.think();
                    v.repaint();
                    counter++;
                    v.setCountdown(setupsteps - counter);
                    Thread.sleep(5);
                }
            }
            counter = 0;
        } else {
            c.initThink(setupsteps);
        }
        while(counter < timesteps) {
            synchronized(ControllerTest.class) {
                ship.update(c.getAction());
                c.think();
                v.repaint();
                counter++;
                Thread.sleep(20);
            }
        }
        System.out.println("Target position: " + destPos);
        System.out.println("Final position: " + ship.s);
        System.out.println("Target velocity: " + destVel);
        System.out.println("Final velocity: " + ship.v);

        System.out.println("Pos Error: " + (destPos.dist(ship.s)));
        System.out.println("Vel Error: " + (destVel.dist(ship.v)));
    }
}