package com.company.simulated_annealing;

import java.util.Collections;

/**
 * Created by Krzysztof Pikóra on 10.09.17.
 */
public class SimulatedAnnealingRun {
    public static void main(String[] args) {
        int howManyCities = 20;
        double temp = 1000;
        double coolingRate = 0.003;

        for (int i = 0; i < howManyCities; i++){
            City city = new City((int)(Math.random()*200), (int)(Math.random()*200));
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

        System.out.println("Final solution distance: " + bestSolution.getDistance());
        System.out.println("Tour: " + bestSolution);
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
