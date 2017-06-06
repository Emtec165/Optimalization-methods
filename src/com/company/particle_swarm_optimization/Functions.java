package com.company.particle_swarm_optimization;

import com.company.Point;

import java.util.List;

/**
 * Created by Krzysztof Pikóra on 07.06.17.
 */
public class Functions {
    public static final double LOC_X_LOW = 1;
    public static final double LOC_X_HIGH = 4;
    public static final double LOC_Y_LOW = -1;
    public static final double LOC_Y_HIGH = 1;
    public static final double VEL_LOW = -1;
    public static final double VEL_HIGH = 1;

    public static final double ERR_TOLERANCE = 1E-20; // the smaller the tolerance, the more accurate the result,
    // but the number of iteration is increased

    public static double evaluate(Point location) {
        double result;
        double x = location.getX(); // the "x" part of the location
        double y = location.getY(); // the "y" part of the location

        result = Math.pow(2.8125 - x + x * Math.pow(y, 4), 2) +
                Math.pow(2.25 - x + x * Math.pow(y, 2), 2) +
                Math.pow(1.5 - x + x * y, 2);

        return result;
    }


    public static int getMinPos(double[] list) {
        int pos = 0;
        double minValue = list[0];

        for(int i=0; i<list.length; i++) {
            if(list[i] < minValue) {
                pos = i;
                minValue = list[i];
            }
        }

        return pos;
    }
}
