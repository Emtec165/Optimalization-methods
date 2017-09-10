package com.company.simulated_annealing;

import java.util.Collections;

/**
 * Created by Krzysztof Pikóra on 10.09.17.
 */
public class SimulatedAnnealingRun {
    private int howManyCities = 20, maxXCoordinate = 200, maxYCoordinate = 200;
    private double temp = 1000, coolingRate = 0.003;

    public void setHomManyCities(int howManyCities){
        this.howManyCities = howManyCities;
    }

    public void setTemp(double temp){
        this.temp = temp;
    }

    public void setCoolingRate(double coolingRate){
        this.coolingRate = coolingRate;
    }

    public void setMaxXCoordinate(int maxXCoordinate){
        this.maxXCoordinate = maxXCoordinate;
    }

    public void setMaxYCoordinate(int maxYCoordinate){
        this.maxYCoordinate = maxYCoordinate;
    }

    public void run() {
        long startTime = System.nanoTime();
        for (int i = 0; i < howManyCities; i++){
            City city = new City((int)(Math.random()*maxXCoordinate), (int)(Math.random()*maxYCoordinate));
            TourManager.addCity(city);
        }

        Tour currentSolution = new Tour();
        currentSolution.generateIndividual();
        Tour bestSolution = new Tour(currentSolution.getTour());
        System.out.println("Initial solution distance: " + currentSolution.getDistance());


        while (temp > 1){
            Tour newSolution = new Tour(currentSolution.getTour());

            int tourPos1 = (int) (newSolution.getTourSize() * Math.random());
            int tourPos2 = (int) (newSolution.getTourSize() * Math.random());
            Collections.swap(newSolution.getTour(), tourPos1, tourPos2);

            int currentEnergy = currentSolution.getDistance();
            int neighbourEnergy = newSolution.getDistance();

            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()){
                currentSolution = new Tour(newSolution.getTour());
            }

            if (currentSolution.getDistance() < bestSolution.getDistance()){
                bestSolution = new Tour(currentSolution.getTour());
            }

            temp *= 1-coolingRate;
        }
        long stopTime = System.nanoTime();


        System.out.println("Final solution distance: " + bestSolution.getDistance());
        System.out.println("Tour: " + bestSolution);
        System.out.println("Execution time " + (stopTime - startTime) + " nanoseconds.\n");

        System.out.println("Parameters:");
        System.out.println("How many cities: " + howManyCities);
        System.out.println("Max temperature: " + temp);
        System.out.println("Cooling rate: " + coolingRate);
        System.out.println("Max city's max x coordinate: " + maxXCoordinate);
        System.out.println("Max city's max y coordinate: " + maxYCoordinate);
    }

    public static  double acceptanceProbability(int energy, int newEnergy, double temperature){
        if (newEnergy < energy){
            return 1f;
        }
        else {
            return Math.exp((energy - newEnergy) / temperature);
        }
    }
}
