package game.ships;

import game.Action;
import game.NonRotationShip;
import math.Vector2d;
import utilities.JEasyFrame;

import java.util.ArrayList;
import java.util.List;

public class ControllerTest {

    public static int timesteps = 100;

    public static void main(String[] args) throws InterruptedException {
        NonRotatingShip ship = new NonRotatingShip(new Vector2d(250,250), new Vector2d(0,0));
        Vector2d destPos = new Vector2d(Math.random()*500,Math.random()*500);
        Controller c = new Controller(ship, destPos);
        ControllerView v = new ControllerView(ship, c, destPos);
        new JEasyFrame(v, "Controller Test");

        int counter = 0;
        while(counter < timesteps) {
            synchronized(ControllerTest.class) {
                ship.update(c.getAction());
                v.repaint();
                counter++;
                Thread.sleep(20);
            }
        }
        System.out.println("Target position: " + destPos);
        System.out.println("Final position: " + ship.s);
        System.out.println("Error: " + (destPos.dist(ship.s)));
    }
}