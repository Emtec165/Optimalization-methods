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
    private List<Particle> pBest = new ArrayList<>();   //particle's best fitness and location
    private Particle sBest = new Particle();            //swarm best fitness and location


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
            p.setVelocity(new Velocity(velX, velY));
            p.calculateFitness();
            swarm.add(p);
        }
    }

    public void execute(){
        updateFitnessList();

        //set particle best fitness, location and swarm best particle
        for (int i = 0; i < SWARM_SIZE; i++){
            pBest.add(swarm.get(i));
        }

        int t = 0;
        double w;
        double err = 9999;

        while (t < ITERATIONS && err > ERR_TOLERANCE){

            //update pBest (particle best)
            update_pBest();

            //update sBest (swarm best)
            update_sBest(t);

            w = W_UP - (((double) t) / ITERATIONS) * (W_UP - W_LO);

            for (int i = 0; i < SWARM_SIZE; i++){
                Particle p = swarm.get(i);



                //update velocity
                double[] newVel = new double[DIMENSIONS];
                updateVelocity(p, w, i, newVel);
                Velocity vel = new Velocity(newVel);
                p.setVelocity(vel);


                //update location
                double[] newLoc = new double[DIMENSIONS];
                newLoc[X] = p.getPosition().getX() + newVel[X];
                newLoc[Y] = p.getPosition().getY() + newVel[Y];
                Point loc = new Point(newLoc);
                p.setPosition(loc);
            }

            err = Functions.evaluate(sBest.getPosition());

            System.out.println("ITERATION " + t);
            printBestParticle();

            updateFitnessList();
            t++;
        }


        // print solution
        System.out.println("\nFound solution at iteration nr " + (t-1));
        printBestParticle();

        if (t == ITERATIONS){
            System.out.println("\nProbably din't found solution...\nTry again.");
        }
    }

    public void updateFitnessList(){
        for (int i = 0; i < SWARM_SIZE; i++){
            swarm.get(i).calculateFitness();
        }
    }

    //update pBest (particle best)
    public void update_pBest(){
        for (int i = 0; i < SWARM_SIZE; i++) {
            Particle pBestTmp = pBest.get(i);
            Particle particleFromSwarm = swarm.get(i);
            if (pBestTmp.getFitness() > particleFromSwarm.getFitness()){
                pBestTmp.setFitness(particleFromSwarm.getFitness());
                pBestTmp.setPosition(particleFromSwarm.getPosition());
            }
        }
    }

    //update sBest (swarm best)
    public void update_sBest(int t){
        int bestParticleIndex = Functions.getMinPos(swarm);
        if (t == 0 || swarm.get(bestParticleIndex).getFitness() < sBest.getFitness()){
            sBest.setFitness(swarm.get(bestParticleIndex).getFitness());
            sBest.setPosition(swarm.get(bestParticleIndex).getPosition());
        }
    }

    public void updateVelocity(Particle p, double w, int i, double[] newVel){
        double r1 = generator.nextDouble();
        double r2 = generator.nextDouble();

        newVel[X] = (w * p.getVelocity().getX()) +
                (r1 * C1) * (pBest.get(i).getPosition().getX() - p.getPosition().getX()) +
                (r2 * C2) * (sBest.getPosition().getX() - p.getPosition().getX());

        newVel[Y] = (w * p.getVelocity().getY()) +
                (r1 * C1) * (pBest.get(i).getPosition().getY() - p.getPosition().getY()) +
                (r2 * C2) * (sBest.getPosition().getY() - p.getPosition().getY());
    }

    public void printBestParticle(){
        System.out.printf("x:%.10f   y:%.10f   fitness:%.10f\n",
                sBest.getPosition().getX(),
                sBest.getPosition().getY(),
                Functions.evaluate(sBest.getPosition()));
    }
}
