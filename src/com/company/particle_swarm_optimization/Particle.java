package com.company.particle_swarm_optimization;

import com.company.Point;

/**
 * Created by Krzysztof Pikóra on 06.06.17.
 */
public class Particle {
    private Point position;
    private Point velocity;
    private double fitness;

    public double getFitness(){
        this.calculateFitness();
        return fitness;
    }

    private void calculateFitness(){
        double x = this.position.getX();
        double y = this.position.getY();

        //FUNCTION
        fitness = Math.pow((2.8125 - x + x * Math.pow(y, 4)), 2) +
                Math.pow((2.25 - x + x * Math.pow(y, 2)), 2) +
                Math.pow((1.5 - x + x * y), 2);
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
