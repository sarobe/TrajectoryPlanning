package game;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date: 10/10/12
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 */
public class ShipTest {
    public static void main(String[] args) {
        ProblemInstance pi = new ProblemInstance(10);
        Ship ship = pi.newShip();
        System.out.println(ship);
        ship.update(new Action(1, 1, 1));
        System.out.println(ship);
    }
}
