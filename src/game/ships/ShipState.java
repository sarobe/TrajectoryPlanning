package game.ships;

import game.NonRotationShip;
import math.Vector2d;

public class ShipState {
    public Vector2d s;
    public Vector2d v;

    public ShipState(NonRotationShip ship) {
        s = ship.s.copy();
        v = ship.v.copy();
    }

    public void loadStateInto(NonRotationShip ship) {
        ship.s = s;
        ship.v = v;
    }
}
