package com.company.particle_swarm_optimization;

import com.company.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.company.particle_swarm_optimization.Functions.*;

/**
 * Created by Krzysztof Pikóra on 06.06.17.
 */
public class PSO implements PSO_Constants{
    Random generator = new Random();
    private List<Particle> swarm = new ArrayList<>();
    private double[] pBest = new double[SWARM_SIZE];    //particle best fitness value
    private List<Point> pBestLocation = new ArrayList<>();  //at location
    private Double sBest;    //swarm best
    private Point sBestLocation;
    private double[] fitnessValueList = new double[SWARM_SIZE];


    public PSO(){

    }


    public void initializeSwarm(){
        Particle p;

        for (int i = 0; i < SWARM_SIZE; i++){
            p = new Particle();

            double posX = LOC_X_LOW + generator.nextDouble() * (LOC_X_HIGH - LOC_X_LOW);
            double posY = LOC_Y_HIGH + generator.nextDouble() * (LOC_Y_HIGH - LOC_Y_LOW);

            double velX = VEL_LOW + generator.nextDouble() * (VEL_HIGH - VEL_LOW);
            double velY = VEL_LOW + generator.nextDouble() * (VEL_HIGH - VEL_LOW);

            p.setPosition(new Point(posX, posY));
            p.setVelocity(new Point(velX, velY));
            p.getFitness();
            swarm.add(p);
        }
    }

    public void execute(){
        updateFitnessList();

        //set particle best fitness, location and swarm best particle
        for (int i = 0; i < SWARM_SIZE; i++){
            pBest[i] = fitnessValueList[i];
            pBestLocation.add(i, swarm.get(i).getPosition());
        }

        int t = 0;
        double w;
        double err = 9999;

        while (t < ITERATIONS && err > ERR_TOLERANCE){

            //update pBest (particle best)
            for (int i = 0; i < SWARM_SIZE; i++) {
                if (pBest[i] > fitnessValueList[i]){
                    pBest[i] = fitnessValueList[i];
                    pBestLocation.set(i, swarm.get(i).getPosition());
                }
            }

            //update sBest (swarm best)
            int bestParticleIndex = Functions.getMinPos(fitnessValueList);
            if(t == 0 || fitnessValueList[bestParticleIndex] < sBest) {
                sBest = fitnessValueList[bestParticleIndex];
                sBestLocation = swarm.get(bestParticleIndex).getPosition();
            }

            w = W_UP - (((double) t) / ITERATIONS) * (W_UP - W_LO);

            for (int i = 0; i < SWARM_SIZE; i++){
                double r1 = generator.nextDouble();
                double r2 = generator.nextDouble();

                Particle p = swarm.get(i);



                //update velocity
                double[] newVel = new double[DIMENSIONS];

                newVel[X] = (w * p.getVelocity().getX()) +
                        (r1 * C1) * (pBestLocation.get(i).getX() - p.getPosition().getX()) +
                        (r2 * C2) * (sBestLocation.getX() - p.getPosition().getX());

                newVel[Y] = (w * p.getVelocity().getY()) +
                        (r1 * C1) * (pBestLocation.get(i).getY() - p.getPosition().getY()) +
                        (r2 * C2) * (sBestLocation.getY() - p.getPosition().getY());

                Point vel = new Point(newVel);
                p.setVelocity(vel);


                //update location
                double[] newLoc = new double[DIMENSIONS];
                newLoc[X] = p.getPosition().getX() + newVel[X];
                newLoc[Y] = p.getPosition().getY() + newVel[Y];
                Point loc = new Point(newLoc);
                p.setPosition(loc);
            }

            err = Functions.evaluate(sBestLocation);

            System.out.println("ITERATION " + t);
            System.out.printf("x:%.3f   y:%.3f   fitness:%.3f err:%.3f\n",
                    sBestLocation.getX(),
                    sBestLocation.getY(),
                    Functions.evaluate(sBestLocation),
                    err);


            updateFitnessList();
            t++;
        }


        // print best particle
        System.out.printf("\nFound solution at iteration nr %d\nx:%.3f   y:%.3f   fitness:%.3f err:%.3f\n",
                t,
                sBestLocation.getX(),
                sBestLocation.getY(),
                Functions.evaluate(sBestLocation),
                err);
    }

    public void updateFitnessList(){
        for (int i = 0; i < SWARM_SIZE; i++){
            fitnessValueList[i] = swarm.get(i).getFitness();
        }
    }
}
