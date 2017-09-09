package com.company.simulated_annealing;

/**
 * Created by Krzysztof Pikóra on 10.09.17.
 */
public class SimulatedAnnealingRun {
    public static void main(String[] args) {
        City city = new City();
        System.out.println(city.distanceToCity(new City(2,5)));
    }
}
