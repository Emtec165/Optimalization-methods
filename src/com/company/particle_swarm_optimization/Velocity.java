package com.company.particle_swarm_optimization;

import com.company.Point;

/**
 * Created by Krzysztof Pikóra on 07.06.17.
 */
public class Velocity extends Point {

    public Velocity(double x, double y){
        super(x, y);
    }

    public Velocity(double[] xy){
        super(xy);
    }
}
