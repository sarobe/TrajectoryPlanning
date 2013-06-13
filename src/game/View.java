package game;

import math.Vector2d;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JComponent {
    Color bg = new Color(128, 128, 200);
    Color border = new Color(64, 64, 128);
    ProblemInstance pi;
    ArrayList<Vector2d> wpList;

    public View(ProblemInstance pi) {
        this.pi = pi;
        wpList = new ArrayList<Vector2d>();
    }

    public void reset() {
        wpList.clear();
    }

    public void paintComponent(Graphics go) {
        // System.out.println("Painting");
        Graphics2D g = (Graphics2D) go;
        g.setColor(border);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.translate(pi.border, pi.border);
        g.setColor(bg);
        g.fillRect(0, 0, pi.size, pi.size);
        for (WayPoint wp : pi.wpl) {
            wp.draw(g);
        }
        pi.getShip().draw(g);
        for (int i=0; i< wpList.size()-1; i++) {
            drawLine(g, wpList.get(i), wpList.get(i+1));
        }
    }

    private void drawLine(Graphics2D g, Vector2d a, Vector2d b) {
        /// System.out.println(a + " : " + b);
        g.setColor(Color.white);
        g.drawLine((int) a.x, (int) a.y, (int) b.x, (int) b.y);
        g.setColor(Color.red);
        g.fillOval((int) b.x-2, (int) b.y -2, 4, 4);
    }

    public Dimension getPreferredSize() {
        int s = 2 * pi.border + pi.size;
        return new Dimension(s, s);
    }
}
