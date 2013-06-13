package game;

import java.util.ArrayList;
import java.util.List;

public class ProblemLibrary {
    public static ProblemInstance classicTwo() {
        return getProblem(
                new double[][]{{200, 200}, {400, 200}}
        );
    }

    public static ProblemInstance getProblem(double[][] a) {
        ArrayList<WayPoint> list = new ArrayList<WayPoint>();
        for (double[] w : a) list.add(new WayPoint(w[0], w[1]));
        return new ProblemInstance(list);
    }

    private static List<WayPoint> toList(WayPoint[] wpa) {
        ArrayList<WayPoint> list = new ArrayList<WayPoint>();
        for (WayPoint wp : wpa) list.add(wp);
        return list;
    }
}
