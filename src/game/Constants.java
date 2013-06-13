package game;

import java.awt.*;
import java.util.Random;

/**
 * Created by Simon M. Lucas
 * sml@essex.ac.uk
 * Date: 26/12/11
 * Time: 12:00
 */
public interface Constants {
    double saucerProb = 0.005;
    int width = 640;
    int height = 480;
    Dimension size = new Dimension(width, height);
    Color bg = Color.black;
    Font font = new Font("Courier", Font.PLAIN, 20);

    int delay = 20;
    double ac = 0.01;
    double t = 1.0;
    Random rand = new Random();

    int clockwise = 1;
    int antiClockwise = -1;
    int noRotate = 0;

}
