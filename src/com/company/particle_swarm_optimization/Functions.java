package com.company.particle_swarm_optimization;

import com.company.Point;

import java.util.List;

/**
 * Created by Krzysztof Pikóra on 07.06.17.
 */
public class Functions {
    public static final double LOC_X_LOW = -50;
    public static final double LOC_X_HIGH = 50;
    public static final double LOC_Y_LOW = -50;
    public static final double LOC_Y_HIGH = 50;
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


//        result = x*x + y*y;

//        result = Math.pow(1 - x, 2) + 100 * Math.pow((y - Math.pow(x,2)), 2);

        return result;
    }

    public static int getMinPos(List<Particle> list) {
        int pos = 0;
        double minValue = list.get(0).getFitness();

        for(int i=0; i<list.size(); i++) {
            double fitness = list.get(i).getFitness();
            if(fitness < minValue) {
                pos = i;
                minValue = fitness;
            }
        }

        return pos;
}
}
