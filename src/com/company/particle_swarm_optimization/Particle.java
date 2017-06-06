package com.company.particle_swarm_optimization;

import com.company.Point;


/**
 * Created by Krzysztof Pikóra on 06.06.17.
 */
public class Particle {
    private Point position;
    private Point velocity;
    private double fitness;

    public Particle(){
        position = new Point(-1,-1);
        velocity = new Point(-1,-1);
        fitness = 0;
    }



    public double getFitness(){
        fitness = Functions.evaluate(position);
        return fitness;
    }

    public Point getPosition(){
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getVelocity() {
        return velocity;
    }

    public void setVelocity(Point velocity) {
        this.velocity = velocity;
    }
}
