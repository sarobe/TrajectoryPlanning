package game.ships;

import math.Vector2d;

import javax.swing.*;
import java.awt.*;

public class ControllerView extends JComponent {

    public NonRotatingShip ship;
    public Controller cont;
    public Vector2d target;
    private int targetRadius = 3;

    public ControllerView(NonRotatingShip ship, Controller cont, Vector2d target) {
        this.ship = ship;
        this.cont = cont;
        this.target = target;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.RED);
        g.fillOval(((int)target.x) - targetRadius, ((int)target.y) - targetRadius, targetRadius*2, targetRadius*2);

        ship.draw((Graphics2D)g);


    }

    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }
}
