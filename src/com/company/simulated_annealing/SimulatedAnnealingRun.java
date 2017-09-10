package com.company.simulated_annealing;

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

        System.out.println(bestSolution);
        System.out.println(bestSolution.getDistance());
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
